package com.turkcell.rentACar2.business.abstracts;

import com.turkcell.rentACar2.business.dtos.ColorDto.ColorGetDto;
import com.turkcell.rentACar2.business.dtos.ColorDto.ColorListDto;
import com.turkcell.rentACar2.business.requests.ColorRequest.CreateColorRequest;
import com.turkcell.rentACar2.business.requests.ColorRequest.UpdateColorRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;

import java.util.List;

public interface ColorService {
    DataResult<List<ColorListDto>> getAll();

    Result add(CreateColorRequest createColorRequest);

    DataResult<ColorGetDto> getById(int id);

    Result update(int id, UpdateColorRequest updateColorRequest);

    Result delete(int id);
}
