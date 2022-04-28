package com.turkcell.rentACar2.business.abstracts;

import com.turkcell.rentACar2.api.models.RentalCarModel;
import com.turkcell.rentACar2.api.models.ReturnedRentalCarModel;
import com.turkcell.rentACar2.business.dtos.RentalCarDto.RentalCarGetDto;
import com.turkcell.rentACar2.business.dtos.RentalCarDto.RentalCarListDto;
import com.turkcell.rentACar2.business.requests.RentalCarRequest.UpdateRentalCarRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import com.turkcell.rentACar2.entities.concretes.RentalCar;

import java.util.List;

public interface RentalCarService {
    DataResult<List<RentalCarListDto>> getAll();

    DataResult<RentalCarGetDto> getById(int id);

    DataResult<RentalCar> add(RentalCarModel rentalCarModel);

    Result update(int id, UpdateRentalCarRequest updateRentalCarRequest);

    Result updateForReturnFromRental(int id, ReturnedRentalCarModel returnedRentalCarModel);

    Result delete(int id);

    DataResult<List<RentalCarListDto>> getByCarId(int carId);

    RentalCar findById(int id);

    void checkIfCarRented(int carId);

    void checkIfIdExists(int id);

    long calculateRentalDayDiff(RentalCar rentalCar);

    long calculateDelayedRentalDayDiff(int rentalCarId);
}
