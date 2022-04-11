package com.turkcell.rentACar2.business.concretes;

import com.turkcell.rentACar2.business.abstracts.CorporateCustomerService;
import com.turkcell.rentACar2.business.constants.messages.BusinessMessages;
import com.turkcell.rentACar2.business.dtos.CorporateCustomerDto.CorporateCustomerGetDto;
import com.turkcell.rentACar2.business.dtos.CorporateCustomerDto.CorporateCustomerListDto;
import com.turkcell.rentACar2.business.requests.CorporateCustomerRequest.CreateCorporateCustomerRequest;
import com.turkcell.rentACar2.business.requests.CorporateCustomerRequest.UpdateCorporateCustomerRequest;
import com.turkcell.rentACar2.core.utilities.exception.BusinessException;
import com.turkcell.rentACar2.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import com.turkcell.rentACar2.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar2.core.utilities.results.SuccessResult;
import com.turkcell.rentACar2.dataAccess.abstracts.CorporateCustomerDao;
import com.turkcell.rentACar2.entities.concretes.CorporateCustomer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CorporateCustomerManager implements CorporateCustomerService {

    private CorporateCustomerDao corporateCustomerDao;
    private ModelMapperService modelMapperService;

    @Override
    public DataResult<List<CorporateCustomerListDto>> getAll() {
        List<CorporateCustomer> corporateCustomers = this.corporateCustomerDao.findAll();

        List<CorporateCustomerListDto> corporateCustomerListDto = corporateCustomers.stream().map(corporateCustomer -> this.modelMapperService.forDto().map(corporateCustomer, CorporateCustomerListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(corporateCustomerListDto, BusinessMessages.DATA_LISTED);
    }

    @Override
    public DataResult<CorporateCustomerGetDto> getById(int id) {
        checkIfCorporateCustomerIdExists(id);

        CorporateCustomer corporateCustomer = this.corporateCustomerDao.getById(id);

        CorporateCustomerGetDto corporateCustomerGetDto = this.modelMapperService.forDto().map(corporateCustomer, CorporateCustomerGetDto.class);

        return new SuccessDataResult<>(corporateCustomerGetDto, BusinessMessages.DATA_BROUGHT + id);
    }

    @Override
    public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
        CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class);
        corporateCustomer.setRegistrationDate(LocalDateTime.now());

        this.corporateCustomerDao.save(corporateCustomer);

        return new SuccessResult(BusinessMessages.DATA_ADDED);
    }

    @Override
    public Result update(int id, UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
        checkIfCorporateCustomerIdExists(id);

        CorporateCustomer corporateCustomer = this.corporateCustomerDao.getById(id);

        populateCorporateCustomerFields(updateCorporateCustomerRequest, corporateCustomer);

        this.corporateCustomerDao.save(corporateCustomer);

        return new SuccessResult(BusinessMessages.DATA_UPDATED + id);
    }

    @Override
    public Result delete(int id) {
        checkIfCorporateCustomerIdExists(id);

        this.corporateCustomerDao.deleteById(id);

        return new SuccessResult(BusinessMessages.DATA_DELETED + id);
    }

    private void checkIfCorporateCustomerIdExists(int id) {
        if (!this.corporateCustomerDao.existsById(id)) {
            throw new BusinessException(BusinessMessages.CUSTOMER_DOES_NOT_EXISTS);
        }
    }

    private void populateCorporateCustomerFields(UpdateCorporateCustomerRequest updateCorporateCustomerRequest, CorporateCustomer corporateCustomer) {
        corporateCustomer.setCompanyName(updateCorporateCustomerRequest.getCompanyName());
        corporateCustomer.setTaxNumber(updateCorporateCustomerRequest.getTaxNumber());
    }
}
