package com.turkcell.rentACar2.business.abstracts;

import com.turkcell.rentACar2.business.dtos.RentalCarDto.RentalCarGetDto;
import com.turkcell.rentACar2.business.dtos.RentalCarDto.RentalCarListDto;
import com.turkcell.rentACar2.business.models.RentalCarModel;
import com.turkcell.rentACar2.business.requests.RentalCarRequest.UpdateRentalCarRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import com.turkcell.rentACar2.entities.concretes.RentalCar;

import java.util.List;

public interface RentalCarService {
    DataResult<List<RentalCarListDto>> getAll();

    DataResult<RentalCarGetDto> getById(int id);

    Result add(RentalCarModel rentalCarModel);

    Result update(int id, UpdateRentalCarRequest updateRentalCarRequest);

    Result delete(int id);

    DataResult<List<RentalCarListDto>> getByCarId(int carId);

    RentalCar findById(int id);

    void checkIfCarRented(int carId);
}
