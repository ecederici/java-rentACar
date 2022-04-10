package com.turkcell.rentACar2.business.abstracts;

import com.turkcell.rentACar2.business.dtos.CityDto.CityGetDto;
import com.turkcell.rentACar2.business.dtos.CityDto.CityListDto;
import com.turkcell.rentACar2.business.requests.CityRequest.CreateCityRequest;
import com.turkcell.rentACar2.business.requests.CityRequest.UpdateCityRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;

import java.util.List;

public interface CityService {
    DataResult<List<CityListDto>> getAll();

    DataResult<CityGetDto> getById(int id);

    Result add(CreateCityRequest createCityRequest);

    Result update(int id, UpdateCityRequest updateCityRequest);

    Result delete(int id);

    void checkIfCityIdExists(int id);
}
