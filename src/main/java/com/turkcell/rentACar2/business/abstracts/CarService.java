package com.turkcell.rentACar2.business.abstracts;

import com.turkcell.rentACar2.business.dtos.CarDto.CarGetDto;
import com.turkcell.rentACar2.business.dtos.CarDto.CarListDto;
import com.turkcell.rentACar2.business.requests.CarRequest.CreateCarRequest;
import com.turkcell.rentACar2.business.requests.CarRequest.UpdateCarRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CarService {
    DataResult<List<CarListDto>> getAll();

    Result add(CreateCarRequest createCarRequest);

    DataResult<CarGetDto> getById(int id);

    Result update(int id, UpdateCarRequest updateCarRequest);

    Result delete(int id);

    DataResult<List<CarListDto>> findByDailyPriceLessThanEqual(double dailyPrice);

    DataResult<List<CarListDto>> getAllPaged(int pageNo, int pageSize);

    DataResult<List<CarListDto>> getAllSorted(Sort.Direction sort);

    DataResult<List<CarListDto>> findCarsByModelYear(int modelYear);

    DataResult<List<CarListDto>> findByColorId(int colorId);

    void populateCurrentDistance(int id, int currentDistance);
}
