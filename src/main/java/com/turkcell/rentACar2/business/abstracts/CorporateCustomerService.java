package com.turkcell.rentACar2.business.abstracts;

import com.turkcell.rentACar2.business.dtos.CorporateCustomerDto.CorporateCustomerGetDto;
import com.turkcell.rentACar2.business.dtos.CorporateCustomerDto.CorporateCustomerListDto;
import com.turkcell.rentACar2.business.requests.CorporateCustomerRequest.CreateCorporateCustomerRequest;
import com.turkcell.rentACar2.business.requests.CorporateCustomerRequest.UpdateCorporateCustomerRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;

import java.util.List;

public interface CorporateCustomerService {

    DataResult<List<CorporateCustomerListDto>> getAll();

    DataResult<CorporateCustomerGetDto> getById(int id);

    Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);

    Result update(int id, UpdateCorporateCustomerRequest updateCorporateCustomerRequest);

    Result delete(int id);
}
