package com.turkcell.rentACar2.business.concretes;

import com.turkcell.rentACar2.business.abstracts.BrandService;
import com.turkcell.rentACar2.business.abstracts.CarService;
import com.turkcell.rentACar2.business.abstracts.ColorService;
import com.turkcell.rentACar2.business.constants.messages.BusinessMessages;
import com.turkcell.rentACar2.business.dtos.CarDto.CarGetDto;
import com.turkcell.rentACar2.business.dtos.CarDto.CarListDto;
import com.turkcell.rentACar2.business.requests.CarRequest.CreateCarRequest;
import com.turkcell.rentACar2.business.requests.CarRequest.UpdateCarRequest;
import com.turkcell.rentACar2.core.utilities.exception.BusinessException;
import com.turkcell.rentACar2.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import com.turkcell.rentACar2.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar2.core.utilities.results.SuccessResult;
import com.turkcell.rentACar2.dataAccess.abstracts.CarDao;
import com.turkcell.rentACar2.entities.concretes.Car;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarDao carDao;
    private final ColorService colorService;
    private final BrandService brandService;
    private final ModelMapperService modelMapperService;

    @Override
    public DataResult<List<CarListDto>> getAll() {
        List<Car> result = this.carDao.findAll();

        List<CarListDto> response = result.stream().map(car -> this.modelMapperService.forDto().map(car, CarListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(response, BusinessMessages.DATA_LISTED);
    }

    @Override
    public Result add(CreateCarRequest createCarRequest) {
        Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);

        checkIfColorExists(car);
        checkIfBrandExists(car);

        this.carDao.save(car);

        return new SuccessResult(BusinessMessages.DATA_ADDED);
    }

    @Override
    public DataResult<CarGetDto> getById(int id) {
        checkIfIdExists(id);

        Car car = this.carDao.getById(id);
        CarGetDto response = this.modelMapperService.forDto().map(car, CarGetDto.class);

        return new SuccessDataResult<>(response,BusinessMessages.DATA_BROUGHT + id);
    }

    @Override
    public Result update(int id, UpdateCarRequest updateCarRequest) {
        checkIfIdExists(id);

        Car car = this.carDao.getById(id);
        populateRentalCarFields(car, updateCarRequest);

        this.carDao.save(car);

        return new SuccessResult(BusinessMessages.DATA_UPDATED + id);
    }

    @Override
    public Result delete(int id) {
        checkIfIdExists(id);

        this.carDao.deleteById(id);
        return new SuccessResult(BusinessMessages.DATA_DELETED + id);
    }

    @Override
    public DataResult<List<CarListDto>> findByDailyPriceLessThanEqual(double dailyPrice) {
        checkIfDailyPriceValid(dailyPrice);

        List<Car> cars = this.carDao.findByDailyPriceLessThanEqual(dailyPrice);

        List<CarListDto> response = cars.stream().map(car -> this.modelMapperService.forDto().map(car, CarListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(response,BusinessMessages.CARS_LIST_BY_DAILY_PRICE + dailyPrice);
    }

    @Override
    public DataResult<List<CarListDto>> getAllPaged(int pageNo, int pageSize) {
        checkIfPageNoAndSizeValid(pageNo, pageSize);

        Pageable pageable = PageRequest.of(pageNo-1,pageSize);

        List<Car> result = this.carDao.findAll(pageable).getContent();

        List<CarListDto> response = result.stream().map(car -> this.modelMapperService.forDto().map(car, CarListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(response,BusinessMessages.CARS_LIST_BY_PAGE_NO + pageNo);
    }

    @Override
    public DataResult<List<CarListDto>> getAllSorted(Sort.Direction direction) {
        Sort sorted = Sort.by(direction,"dailyPrice");

        List<Car> cars = this.carDao.findAll(sorted);

        List<CarListDto> response = cars.stream().map(car -> this.modelMapperService.forDto().map(car, CarListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(response, BusinessMessages.CARS_SORTED_BY_DAILY_PRICE);
    }

    @Override
    public DataResult<List<CarListDto>> findByColorId(int colorId) {
        List<Car> cars =  this.carDao.findByColorId(colorId);
        List<CarListDto> response = cars.stream().map(car -> this.modelMapperService.forDto().map(car, CarListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(response, BusinessMessages.CARS_SORTED_BY_COLOR_ID + colorId);
    }

    @Override
    public void populateCurrentDistance(int id, int currentDistance) {
        Car car = this.carDao.getById(id);

        car.setCurrentDistance(currentDistance);

        this.carDao.save(car);
    }

    @Override
    public DataResult<List<CarListDto>> findCarsByModelYear(int modelYear) {
        checkIfModelYearValid(modelYear);

        List<Car> cars = this.carDao.findAllByModelYear(modelYear);

        List<CarListDto> response = cars.stream().map(car -> this.modelMapperService.forDto().map(car, CarListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(response,BusinessMessages.CARS_LIST_BY_MODEL_YEAR + modelYear);
    }

    private void checkIfIdExists(int id) {
        if (!this.carDao.existsById(id)) {
            throw new BusinessException(BusinessMessages.CAR_DOES_NOT_EXISTS + id);
        }
    }

    private void populateRentalCarFields(Car car, UpdateCarRequest updateCarRequest) {

        car.setDailyPrice(updateCarRequest.getDailyPrice());
        car.setDescription(updateCarRequest.getDescription());
        car.setCurrentDistance(updateCarRequest.getCurrentDistance());
    }

    private void checkIfColorExists(Car car) {
        if (this.colorService.getById(car.getColor().getId()) == null) {
            throw new BusinessException(BusinessMessages.COLOR_DOES_NOT_EXISTS);
        }
    }

    private void checkIfBrandExists(Car car) {
        if (this.brandService.getById(car.getBrand().getId()) == null) {
            throw new BusinessException(BusinessMessages.BRAND_DOES_NOT_EXISTS);
        }
    }

    private void checkIfDailyPriceValid(double dailyPrice) {
        if (dailyPrice <= 0) {
            throw new BusinessException(BusinessMessages.DAILY_PRICE_DOES_NOT_VALID + dailyPrice);
        }
    }

    private void checkIfPageNoAndSizeValid(int pageNo, int pageSize) {
        if (pageNo <= 0) {
            throw new BusinessException(BusinessMessages.PAGE_NO_DOES_NOT_VALID + pageNo);
        }

        if (pageSize <= 0) {
            throw new BusinessException(BusinessMessages.PAGE_SIZE_DOES_NOT_VALID + pageSize);
        }
    }

    private void checkIfModelYearValid(int modelYear) {
        if (modelYear < 1930) {
            throw new BusinessException(BusinessMessages.MODEL_YEAR_DOES_NOT_VALID + modelYear);
        }
    }
}
