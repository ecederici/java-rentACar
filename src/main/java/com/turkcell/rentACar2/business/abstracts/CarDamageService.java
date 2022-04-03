package com.turkcell.rentACar2.business.abstracts;

import com.turkcell.rentACar2.business.dtos.CarDamageDto.CarDamageGetDto;
import com.turkcell.rentACar2.business.dtos.CarDamageDto.CarDamageListDto;
import com.turkcell.rentACar2.business.requests.CarDamageRequest.CreateCarDamageRequest;
import com.turkcell.rentACar2.business.requests.CarDamageRequest.UpdateCarDamageRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;

import java.util.List;

public interface CarDamageService {
    DataResult<List<CarDamageListDto>> getAll();

    DataResult<CarDamageGetDto> getById(int id);

    Result add(CreateCarDamageRequest createCarDamageRequest);

    Result update(int id, UpdateCarDamageRequest updateCarDamageRequest);

    Result delete(int id);

    DataResult<List<CarDamageListDto>> getByCarId(int carId);
}
