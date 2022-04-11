package com.turkcell.rentACar2.business.concretes;

import com.turkcell.rentACar2.business.abstracts.IndividualCustomerService;
import com.turkcell.rentACar2.business.constants.messages.BusinessMessages;
import com.turkcell.rentACar2.business.dtos.IndividualCustomerDto.IndividualCustomerGetDto;
import com.turkcell.rentACar2.business.dtos.IndividualCustomerDto.IndividualCustomerListDto;
import com.turkcell.rentACar2.business.requests.IndividualCustomerRequest.CreateIndividualCustomerRequest;
import com.turkcell.rentACar2.business.requests.IndividualCustomerRequest.UpdateIndividualCustomerRequest;
import com.turkcell.rentACar2.core.utilities.exception.BusinessException;
import com.turkcell.rentACar2.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import com.turkcell.rentACar2.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar2.core.utilities.results.SuccessResult;
import com.turkcell.rentACar2.dataAccess.abstracts.IndividualCustomerDao;
import com.turkcell.rentACar2.entities.concretes.IndividualCustomer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IndividualCustomerManager implements IndividualCustomerService {

    private IndividualCustomerDao individualCustomerDao;
    private ModelMapperService modelMapperService;

    @Override
    public DataResult<List<IndividualCustomerListDto>> getAll() {
        List<IndividualCustomer> individualCustomers = this.individualCustomerDao.findAll();

        List<IndividualCustomerListDto> individualCustomerListDto = individualCustomers.stream().map(individualCustomer -> this.modelMapperService.forDto().map(individualCustomer, IndividualCustomerListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(individualCustomerListDto, BusinessMessages.DATA_LISTED);
    }

    @Override
    public DataResult<IndividualCustomerGetDto> getById(int id) {
        checkIfIndividualCustomerIdExists(id);

        IndividualCustomer individualCustomer = this.individualCustomerDao.getById(id);
        IndividualCustomerGetDto individualCustomerGetDto = this.modelMapperService.forDto().map(individualCustomer, IndividualCustomerGetDto.class);

        return new SuccessDataResult<>(individualCustomerGetDto, BusinessMessages.DATA_BROUGHT + id);
    }

    @Override
    public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
        individualCustomer.setRegistrationDate(LocalDateTime.now());
        this.individualCustomerDao.save(individualCustomer);

        return new SuccessResult(BusinessMessages.DATA_ADDED);
    }

    @Override
    public Result update(int id, UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
        checkIfIndividualCustomerIdExists(id);

        IndividualCustomer individualCustomer = this.individualCustomerDao.getById(id);

        individualCustomer.setFirstName(updateIndividualCustomerRequest.getFirstName());
        individualCustomer.setLastName(updateIndividualCustomerRequest.getLastName());
        individualCustomer.setNationalIdentity(updateIndividualCustomerRequest.getNationalIdentity());

        this.individualCustomerDao.save(individualCustomer);

        return new SuccessResult(BusinessMessages.DATA_UPDATED + id);
    }

    @Override
    public Result delete(int id) {
        checkIfIndividualCustomerIdExists(id);

        this.individualCustomerDao.deleteById(id);

        return new SuccessResult(BusinessMessages.DATA_DELETED + id);
    }

    private void checkIfIndividualCustomerIdExists(int id) {
        if (!this.individualCustomerDao.existsById(id)) {
            throw new BusinessException(BusinessMessages.CUSTOMER_DOES_NOT_EXISTS);
        }
    }
}
