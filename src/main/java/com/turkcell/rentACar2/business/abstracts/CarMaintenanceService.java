package com.turkcell.rentACar2.business.abstracts;

import com.turkcell.rentACar2.business.dtos.CarMaintenanceDto.CarMaintenanceGetDto;
import com.turkcell.rentACar2.business.dtos.CarMaintenanceDto.CarMaintenanceListDto;
import com.turkcell.rentACar2.business.requests.CarMaintenanceRequest.CreateCarMaintenanceRequest;
import com.turkcell.rentACar2.business.requests.CarMaintenanceRequest.UpdateCarMaintenanceRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;

import java.util.List;

public interface CarMaintenanceService {
    DataResult<List<CarMaintenanceListDto>> getAll();

    DataResult<CarMaintenanceGetDto> getById(int id);

    Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest);

    Result update(int id, UpdateCarMaintenanceRequest updateCarMaintenanceRequest);

    Result delete(int id);

    DataResult<List<CarMaintenanceListDto>> getByCarId(int carId);

    void checkIfCarInMaintenance(int carId);
}
