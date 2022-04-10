package com.turkcell.rentACar2.business.abstracts;

import com.turkcell.rentACar2.business.dtos.AdditionalServiceDto.AdditionalServiceGetDto;
import com.turkcell.rentACar2.business.dtos.AdditionalServiceDto.AdditionalServiceListDto;
import com.turkcell.rentACar2.business.requests.AdditionalServiceRequest.CreateAdditionalServiceRequest;
import com.turkcell.rentACar2.business.requests.AdditionalServiceRequest.UpdateAdditionalServiceRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;

import java.util.List;

public interface AdditionalServiceService {

    DataResult<List<AdditionalServiceListDto>> getAll();

    DataResult<AdditionalServiceGetDto> getById(int id);

    Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest);

    Result update(int id, UpdateAdditionalServiceRequest updateAdditionalServiceRequest);

    Result delete(int id);
}
