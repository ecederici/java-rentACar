package com.turkcell.rentACar2.business.concretes;

import com.turkcell.rentACar2.business.abstracts.CarMaintenanceService;
import com.turkcell.rentACar2.business.abstracts.CarService;
import com.turkcell.rentACar2.business.abstracts.RentalCarService;
import com.turkcell.rentACar2.business.constants.messages.BusinessMessages;
import com.turkcell.rentACar2.business.dtos.CarMaintenanceDto.CarMaintenanceGetDto;
import com.turkcell.rentACar2.business.dtos.CarMaintenanceDto.CarMaintenanceListDto;
import com.turkcell.rentACar2.business.requests.CarMaintenanceRequest.CreateCarMaintenanceRequest;
import com.turkcell.rentACar2.business.requests.CarMaintenanceRequest.UpdateCarMaintenanceRequest;
import com.turkcell.rentACar2.core.utilities.exception.BusinessException;
import com.turkcell.rentACar2.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import com.turkcell.rentACar2.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar2.core.utilities.results.SuccessResult;
import com.turkcell.rentACar2.dataAccess.abstracts.CarMaintenanceDao;
import com.turkcell.rentACar2.entities.concretes.CarMaintenance;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {
    private final CarMaintenanceDao carMaintenanceDao;
    private final ModelMapperService modelMapperService;
    private final RentalCarService rentalCarService;
    private final CarService carService;

    public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao,
                                 ModelMapperService modelMapperService,
                                 @Lazy RentalCarService rentalCarService,
                                 CarService carService) {
        this.carMaintenanceDao = carMaintenanceDao;
        this.modelMapperService = modelMapperService;
        this.rentalCarService = rentalCarService;
        this.carService = carService;
    }

    @Override
    public DataResult<List<CarMaintenanceListDto>> getAll() {
        List<CarMaintenance> result = this.carMaintenanceDao.findAll();

        List<CarMaintenanceListDto> response = result.stream()
                .map(carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(response, BusinessMessages.DATA_LISTED);
    }

    @Override
    public DataResult<CarMaintenanceGetDto> getById(int id) {
        checkIfIdExists(id);

        CarMaintenance carMaintenance = this.carMaintenanceDao.getById(id);

        CarMaintenanceGetDto response = this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceGetDto.class);

        return new SuccessDataResult<>(response, BusinessMessages.DATA_BROUGHT + id);
    }

    @Override
    public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) {
        checkIfCarIdExists(createCarMaintenanceRequest.getCarId());
        checkIfCarRented(createCarMaintenanceRequest.getCarId());
        checkIfCarInMaintenance(createCarMaintenanceRequest.getCarId());

        CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(createCarMaintenanceRequest, CarMaintenance.class);

        carMaintenance.setId(0);
        this.carMaintenanceDao.save(carMaintenance);

        return new SuccessResult(BusinessMessages.DATA_ADDED);
    }

    @Override
    public Result update(int id, UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
        checkIfIdExists(id);

        CarMaintenance carMaintenance = this.carMaintenanceDao.getById(id);

        populateCarMaintenanceFields(carMaintenance, updateCarMaintenanceRequest);

        this.carMaintenanceDao.save(carMaintenance);

        return new SuccessResult(BusinessMessages.DATA_UPDATED + id);

    }

    @Override
    public Result delete(int id) {
        checkIfIdExists(id);

        this.carMaintenanceDao.deleteById(id);
        return new SuccessResult(BusinessMessages.DATA_DELETED + id);
    }

    @Override
    public DataResult<List<CarMaintenanceListDto>> getByCarId(int carId) {

        List<CarMaintenance> carMaintenances = this.carMaintenanceDao.findAllByCar_Id(carId);
        List<CarMaintenanceListDto> response = carMaintenances.stream().map(carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(response, BusinessMessages.DATA_LISTED_BY_CAR_ID + carId);
    }

    @Override
    public void checkIfCarInMaintenance(int carId) {
        List<CarMaintenance> carMaintenances = this.carMaintenanceDao.findAllByCar_Id(carId);

        if (!carMaintenances.isEmpty()) {
            for (CarMaintenance carMaintenance : carMaintenances) {
                if(carMaintenance.getReturnDate() == null) {
                    throw new BusinessException(BusinessMessages.CAR_IN_MAINTENANCE);
                }
            }
        }
    }

    private void checkIfIdExists(int id) {
        if (!this.carMaintenanceDao.existsById(id)) {
            throw new BusinessException(BusinessMessages.CAR_MAINTENANCE_DOES_NOT_EXISTS + id);
        }
    }

    private void populateCarMaintenanceFields(CarMaintenance carMaintenance, UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
        carMaintenance.setDescription(updateCarMaintenanceRequest.getDescription());
        carMaintenance.setReturnDate(updateCarMaintenanceRequest.getReturnDate());
    }

    private void checkIfCarIdExists(int carId) {
        this.carService.checkIfIdExists(carId);
    }

    private void checkIfCarRented(int carId) {
        this.rentalCarService.checkIfCarRented(carId);
    }
}
