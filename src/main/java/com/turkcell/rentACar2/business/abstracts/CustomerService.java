package com.turkcell.rentACar2.business.abstracts;

import com.turkcell.rentACar2.business.dtos.CustomerDto.CustomerGetDto;
import com.turkcell.rentACar2.business.dtos.CustomerDto.CustomerListDto;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import com.turkcell.rentACar2.entities.concretes.Customer;

import java.util.List;

public interface CustomerService {
    DataResult<List<CustomerListDto>> getAll();

    DataResult<CustomerGetDto> getById(int id);

    Result delete(int id);

    void checkIfCustomerIdExists(int id);

    Customer findById(int id);

    void checkIfEmailExists(String email);
}
