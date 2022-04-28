package com.turkcell.rentACar2.business.concretes;

import com.turkcell.rentACar2.api.models.RentalCarModel;
import com.turkcell.rentACar2.api.models.ReturnedRentalCarModel;
import com.turkcell.rentACar2.business.abstracts.*;
import com.turkcell.rentACar2.business.constants.messages.BusinessMessages;
import com.turkcell.rentACar2.business.dtos.CarDto.CarGetDto;
import com.turkcell.rentACar2.business.dtos.RentalCarDto.RentalCarGetDto;
import com.turkcell.rentACar2.business.dtos.RentalCarDto.RentalCarListDto;
import com.turkcell.rentACar2.business.requests.RentalCarRequest.CreateRentalCarRequest;
import com.turkcell.rentACar2.business.requests.RentalCarRequest.UpdateRentalCarRequest;
import com.turkcell.rentACar2.core.utilities.exception.BusinessException;
import com.turkcell.rentACar2.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import com.turkcell.rentACar2.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar2.core.utilities.results.SuccessResult;
import com.turkcell.rentACar2.dataAccess.abstracts.RentalCarDao;
import com.turkcell.rentACar2.entities.concretes.City;
import com.turkcell.rentACar2.entities.concretes.RentalCar;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class RentalCarManager implements RentalCarService {

    private RentalCarDao rentalCarDao;
    private ModelMapperService modelMapperService;
    private CarMaintenanceService carMaintenanceService;
    private CarService carService;
    private CityService cityService;
    private CustomerService customerService;
    private OrderedAdditionalServiceService orderedAdditionalServiceService;
    private PaymentService paymentService;

    public RentalCarManager(RentalCarDao rentalCarDao,
                            ModelMapperService modelMapperService,
                            CarMaintenanceService carMaintenanceService,
                            CarService carService,
                            CityService cityService,
                            CustomerService customerService,
                            @Lazy OrderedAdditionalServiceService orderedAdditionalServiceService,
                            @Lazy PaymentService paymentService) {
        this.rentalCarDao = rentalCarDao;
        this.modelMapperService = modelMapperService;
        this.carMaintenanceService = carMaintenanceService;
        this.carService = carService;
        this.cityService = cityService;
        this.customerService = customerService;
        this.orderedAdditionalServiceService = orderedAdditionalServiceService;
        this.paymentService = paymentService;
    }

    @Override
    public DataResult<List<RentalCarListDto>> getAll() {
        List<RentalCar> rentalCars = this.rentalCarDao.findAll();

        List<RentalCarListDto> response = rentalCars.stream().map(rentalCar -> this.modelMapperService.forDto().map(rentalCar, RentalCarListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(response, BusinessMessages.DATA_LISTED);
    }

    @Override
    public DataResult<RentalCarGetDto> getById(int id) {
        checkIfIdExists(id);

        RentalCar rentalCar = this.rentalCarDao.getById(id);
        RentalCarGetDto rentalCarGetDto = this.modelMapperService.forDto().map(rentalCar, RentalCarGetDto.class);

        return new SuccessDataResult<>(rentalCarGetDto,BusinessMessages.DATA_BROUGHT + id);
    }

    @Override
    public DataResult<RentalCar> add(RentalCarModel rentalCarModel) {
        CreateRentalCarRequest rentalCarRequest = rentalCarModel.getCreateRentalCarRequest();
        RentalCar rentalCar = this.modelMapperService.forRequest().map(rentalCarRequest,RentalCar.class);

        checkIfCarInMaintenance(rentalCar.getCar().getId());
        checkIfCitiesExists(rentalCarRequest.getFromCityId(), rentalCarRequest.getToCityId());
        checkIfCustomerExists(rentalCarRequest.getCustomerId());
        checkIfCarRented(rentalCarRequest.getCarId());
        checkIfReturnDateIsAfterOrEqualRentDate(rentalCar.getRentDate(), rentalCar.getReturnDate());

        CarGetDto car = this.carService.getById(rentalCarRequest.getCarId()).getData();
        rentalCar.setCurrentDistance(car.getCurrentDistance());
        rentalCar.setId(0);

        this.rentalCarDao.save(rentalCar);
        populateOrderedAdditionalService(rentalCarModel, rentalCar);

        return new SuccessDataResult<>(rentalCar, BusinessMessages.DATA_ADDED);
    }

    @Override
    public Result update(int id, UpdateRentalCarRequest updateRentalCarRequest) {
        checkIfIdExists(id);

        RentalCar rentalCar = this.rentalCarDao.getById(id);

        checkIfReturnDateIsAfterOrEqualRentDate(rentalCar.getRentDate(), rentalCar.getReturnDate());

        populateRentalCarFields(rentalCar, updateRentalCarRequest);

        this.rentalCarDao.save(rentalCar);

        return new SuccessResult(BusinessMessages.DATA_UPDATED + id);
    }

    @Override
    public Result updateForReturnFromRental(int id, ReturnedRentalCarModel returnedRentalCarModel) {
        checkIfIdExists(id);

        RentalCar rentalCar = this.rentalCarDao.getById(id);

        checkIfExecutionDateExists(rentalCar);
        checkIfLastDistanceIsLessThanBefore(rentalCar, returnedRentalCarModel);

        if (calculateDelayedRentalDayDiff(id) > 0) {
            this.paymentService.createPaymentForDelay(id, returnedRentalCarModel);
        }
        populateRentalCarFieldsForReturned(returnedRentalCarModel, rentalCar);
        this.rentalCarDao.save(rentalCar);

        return new SuccessResult(BusinessMessages.CAR_RETURNED_FROM_RENTAL);
    }

    @Override
    public Result delete(int id) {
        checkIfIdExists(id);

        this.rentalCarDao.deleteById(id);

        return new SuccessResult(BusinessMessages.DATA_DELETED + id);
    }

    @Override
    public DataResult<List<RentalCarListDto>> getByCarId(int carId) {
        List<RentalCar> rentalCars = this.rentalCarDao.findAllByCar_Id(carId);

        List<RentalCarListDto> rentalCarListDto = rentalCars.stream().map(rentalCar -> this.modelMapperService.forDto().map(rentalCar,RentalCarListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(rentalCarListDto,BusinessMessages.DATA_LISTED_BY_CAR_ID);
    }

    @Override
    public RentalCar findById(int id) {
        return this.rentalCarDao.getById(id);
    }

    private void populateRentalCarFields(RentalCar rentalCar, UpdateRentalCarRequest updateRentalCarRequest) {
        rentalCar.setRentDate(updateRentalCarRequest.getRentDate());
        rentalCar.setReturnDate(updateRentalCarRequest.getReturnDate());
        rentalCar.setLastDistance(updateRentalCarRequest.getCarId());

        City fromCity = this.cityService.findById(updateRentalCarRequest.getFromCityId());
        City toCity = this.cityService.findById(updateRentalCarRequest.getToCityId());

        rentalCar.setFromCity(fromCity);
        rentalCar.setToCity(toCity);
    }

    private void populateRentalCarFieldsForReturned(ReturnedRentalCarModel returnedRentalCarModel, RentalCar rentalCar) {
        rentalCar.setExecutionDate(LocalDate.now());
        rentalCar.setLastDistance(returnedRentalCarModel.getUpdateReturnedRentalCarRequest().getLastDistance());
        this.carService.populateCurrentDistance(rentalCar.getCar().getId(),returnedRentalCarModel.getUpdateReturnedRentalCarRequest().getLastDistance());
    }

    @Override
    public void checkIfIdExists(int id) {
        if (!this.rentalCarDao.existsById(id)) {
            throw new BusinessException(BusinessMessages.RENTAL_CAR_DOES_NOT_EXISTS + id);
        }
    }

    private void checkIfCarInMaintenance(int carId) {
        this.carMaintenanceService.checkIfCarInMaintenance(carId);
    }

    @Override
    public void checkIfCarRented(int carId) {
        List<RentalCar> rentalCars = this.rentalCarDao.findAllByCar_Id(carId);

        if (!rentalCars.isEmpty()) {
            for (RentalCar rentalCar : rentalCars) {
                if (rentalCar.getExecutionDate() == null) {
                    throw new BusinessException(BusinessMessages.CAR_RENTED);
                }
            }
        }
    }

    private void checkIfCitiesExists(int fromCity, int toCity) {
       this.cityService.checkIfCityIdExists(fromCity);
       this.cityService.checkIfCityIdExists(toCity);
    }

    private void checkIfCustomerExists(int customerId) {
        this.customerService.checkIfCustomerIdExists(customerId);
    }

    private void checkIfReturnDateIsAfterOrEqualRentDate(LocalDate rentDate, LocalDate returnDate) {
        if (!returnDate.isAfter(rentDate) && !returnDate.equals(rentDate)) {
            throw new BusinessException(BusinessMessages.INVALID_RETURN_DATE);
        }
    }

    private void checkIfLastDistanceIsLessThanBefore(RentalCar rentalCar, ReturnedRentalCarModel returnedRentalCarModel) {
        if (rentalCar.getCar().getCurrentDistance() > returnedRentalCarModel.getUpdateReturnedRentalCarRequest().getLastDistance()) {
            throw new BusinessException(BusinessMessages.INVALID_LAST_DISTANCE);
        }
    }

    private void populateOrderedAdditionalService(RentalCarModel rentalCarModel, RentalCar rentalCar) {
        if (rentalCarModel.getCreateOrderedAdditionalServiceList() != null) {
            this.orderedAdditionalServiceService.add(rentalCarModel.getCreateOrderedAdditionalServiceList(), rentalCar.getId());
        }
    }

    @Override
    public long calculateRentalDayDiff(RentalCar rentalCar) {
        return DAYS.between(rentalCar.getRentDate(), rentalCar.getReturnDate());
    }

    @Override
    public long calculateDelayedRentalDayDiff(int rentalCarId) {
        RentalCar rentalCar = findById(rentalCarId);
        return DAYS.between(rentalCar.getReturnDate(), LocalDate.now());
    }

    private void checkIfExecutionDateExists(RentalCar rentalCar) {
        if (rentalCar.getExecutionDate() != null) {
            throw new BusinessException(BusinessMessages.CAR_ALREADY_RETURNED_FROM_RENTAL);
        }
    }

}
