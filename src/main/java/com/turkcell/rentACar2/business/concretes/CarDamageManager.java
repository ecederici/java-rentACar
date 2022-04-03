package com.turkcell.rentACar2.business.concretes;

import com.turkcell.rentACar2.business.abstracts.CarDamageService;
import com.turkcell.rentACar2.business.abstracts.CarService;
import com.turkcell.rentACar2.business.constants.messages.BusinessMessages;
import com.turkcell.rentACar2.business.dtos.CarDamageDto.CarDamageGetDto;
import com.turkcell.rentACar2.business.dtos.CarDamageDto.CarDamageListDto;
import com.turkcell.rentACar2.business.requests.CarDamageRequest.CreateCarDamageRequest;
import com.turkcell.rentACar2.business.requests.CarDamageRequest.UpdateCarDamageRequest;
import com.turkcell.rentACar2.core.utilities.exception.BusinessException;
import com.turkcell.rentACar2.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import com.turkcell.rentACar2.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar2.core.utilities.results.SuccessResult;
import com.turkcell.rentACar2.dataAccess.abstracts.CarDamageDao;
import com.turkcell.rentACar2.entities.concretes.CarDamage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarDamageManager implements CarDamageService {
    private final CarDamageDao carDamageDao;
    private final ModelMapperService modelMapperService;
    private final CarService carService;

    @Override
    public DataResult<List<CarDamageListDto>> getAll() {
        List<CarDamage> carDamages = this.carDamageDao.findAll();

        List<CarDamageListDto> response = carDamages.stream().map(carDamage -> this.modelMapperService.forDto().map(carDamage,CarDamageListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(response, BusinessMessages.DATA_LISTED);
    }

    @Override
    public DataResult<CarDamageGetDto> getById(int id) {
        this.checkIfIdExists(id);

        CarDamage carDamage = this.carDamageDao.getById(id);

        CarDamageGetDto response = this.modelMapperService.forDto().map(carDamage, CarDamageGetDto.class);

        return new SuccessDataResult<>(response, BusinessMessages.DATA_BROUGHT + id);
    }

    @Override
    public Result add(CreateCarDamageRequest createCarDamageRequest) {
        checkIfCarDamageFieldsExists(createCarDamageRequest);

        CarDamage carDamage = this.modelMapperService.forRequest().map(createCarDamageRequest, CarDamage.class);

        carDamage.setId(0);

        this.carDamageDao.save(carDamage);

        return new SuccessResult(BusinessMessages.DATA_ADDED);
    }

    @Override
    public Result update(int id, UpdateCarDamageRequest updateCarDamageRequest) {
        checkIfIdExists(id);
        CarDamage carDamage = this.carDamageDao.getById(id);

        carDamage.setDescription(updateCarDamageRequest.getDescription());

        this.carDamageDao.save(carDamage);

        return new SuccessResult(BusinessMessages.DATA_UPDATED + id);
    }

    @Override
    public Result delete(int id) {
        this.carDamageDao.deleteById(id);

        return new SuccessResult(BusinessMessages.DATA_DELETED + id);
    }

    @Override
    public DataResult<List<CarDamageListDto>> getByCarId(int carId) {
        checkIfCarIdExists(carId);

        List<CarDamage> carDamages = this.carDamageDao.findByCar_Id(carId);
        List<CarDamageListDto> response = carDamages.stream().map(carDamage -> this.modelMapperService.forDto().map(carDamage, CarDamageListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(response, BusinessMessages.DATA_LISTED_BY_CAR_ID);
    }

    private void checkIfIdExists(int id) {
        if (!this.carDamageDao.existsById(id)) {
            throw new BusinessException(BusinessMessages.CAR_DAMAGE_DOES_NOT_EXISTS + id);
        }
    }

    private void checkIfCarIdExists(int carId) {
        if (this.carService.getById(carId) == null) {
            throw new BusinessException(BusinessMessages.CAR_DAMAGE_DOES_NOT_EXISTS_BY_CAR_ID + carId);
        }
    }

    private void checkIfCarDamageFieldsExists(CreateCarDamageRequest createCarDamageRequest) {
        List<CarDamageListDto> carDamages = this.getByCarId(createCarDamageRequest.getCarId()).getData();

        for (CarDamageListDto carDamage: carDamages) {
            if (carDamage.getDescription().equals(createCarDamageRequest.getDescription()) && carDamage.getExecutionDate().equals(createCarDamageRequest.getExecutionDate())) {
                throw new BusinessException(BusinessMessages.CAR_DAMAGE_EXISTS);
            }
        }
    }
}
