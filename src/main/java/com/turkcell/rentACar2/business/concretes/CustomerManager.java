package com.turkcell.rentACar2.business.concretes;

import com.turkcell.rentACar2.business.abstracts.CustomerService;
import com.turkcell.rentACar2.business.constants.messages.BusinessMessages;
import com.turkcell.rentACar2.business.dtos.CustomerDto.CustomerGetDto;
import com.turkcell.rentACar2.business.dtos.CustomerDto.CustomerListDto;
import com.turkcell.rentACar2.core.utilities.exception.BusinessException;
import com.turkcell.rentACar2.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import com.turkcell.rentACar2.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar2.core.utilities.results.SuccessResult;
import com.turkcell.rentACar2.dataAccess.abstracts.CustomerDao;
import com.turkcell.rentACar2.entities.concretes.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {

    private CustomerDao customerDao;
    private ModelMapperService modelMapperService;

    @Override
    public DataResult<List<CustomerListDto>> getAll() {
        List<Customer> customers = this.customerDao.findAll();
        List<CustomerListDto> customerListDto = customers.stream().map(customer -> this.modelMapperService.forDto().map(customer, CustomerListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(customerListDto, BusinessMessages.DATA_LISTED);
    }

    @Override
    public DataResult<CustomerGetDto> getById(int id) {
        checkIfCustomerIdExists(id);

        Customer customer = this.customerDao.getById(id);

        CustomerGetDto customerGetDto = this.modelMapperService.forDto().map(customer, CustomerGetDto.class);

        return new SuccessDataResult<>(customerGetDto, BusinessMessages.DATA_BROUGHT + id);
    }

    @Override
    public Result delete(int id) {
        checkIfCustomerIdExists(id);

        this.customerDao.deleteById(id);

        return new SuccessResult(BusinessMessages.DATA_DELETED);
    }

    @Override
    public void checkIfCustomerIdExists(int id) {
        if (!this.customerDao.existsById(id)) {
            throw new BusinessException(BusinessMessages.CUSTOMER_DOES_NOT_EXISTS);
        }
    }
}
