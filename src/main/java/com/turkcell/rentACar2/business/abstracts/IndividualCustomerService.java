package com.turkcell.rentACar2.business.abstracts;

import com.turkcell.rentACar2.business.dtos.IndividualCustomerDto.IndividualCustomerGetDto;
import com.turkcell.rentACar2.business.dtos.IndividualCustomerDto.IndividualCustomerListDto;
import com.turkcell.rentACar2.business.requests.IndividualCustomerRequest.CreateIndividualCustomerRequest;
import com.turkcell.rentACar2.business.requests.IndividualCustomerRequest.UpdateIndividualCustomerRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;

import java.util.List;

public interface IndividualCustomerService {

    DataResult<List<IndividualCustomerListDto>> getAll();

    DataResult<IndividualCustomerGetDto> getById(int id);

    Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);

    Result update(int id, UpdateIndividualCustomerRequest updateIndividualCustomerRequest);

    Result delete(int id);
}
