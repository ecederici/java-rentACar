package com.turkcell.rentACar2.business.concretes;

import com.turkcell.rentACar2.business.abstracts.CustomerService;
import com.turkcell.rentACar2.business.abstracts.UserCardInformationService;
import com.turkcell.rentACar2.business.constants.messages.BusinessMessages;
import com.turkcell.rentACar2.business.dtos.UserCardInformationDto.UserCardInformationGetDto;
import com.turkcell.rentACar2.business.dtos.UserCardInformationDto.UserCardInformationListDto;
import com.turkcell.rentACar2.business.requests.UserCardInformationRequest.CreateUserCardInformationRequest;
import com.turkcell.rentACar2.business.requests.UserCardInformationRequest.UpdateUserCardInformationRequest;
import com.turkcell.rentACar2.core.utilities.exception.BusinessException;
import com.turkcell.rentACar2.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import com.turkcell.rentACar2.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar2.core.utilities.results.SuccessResult;
import com.turkcell.rentACar2.dataAccess.abstracts.UserCardInformationDao;
import com.turkcell.rentACar2.entities.concretes.Customer;
import com.turkcell.rentACar2.entities.concretes.UserCardInformation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserCardInformationManager implements UserCardInformationService {
    private UserCardInformationDao userCardInformationDao;
    private ModelMapperService modelMapperService;
    private CustomerService customerService;

    @Override
    public DataResult<List<UserCardInformationListDto>> getAll() {
       List<UserCardInformation> userCardInformations = this.userCardInformationDao.findAll();

        List<UserCardInformationListDto> response = userCardInformations.stream().map(userCardInformation -> this.modelMapperService.forDto().map(userCardInformation, UserCardInformationListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(response, BusinessMessages.DATA_LISTED);
    }

    @Override
    public DataResult<UserCardInformationGetDto> getById(int id) {
        checkIfIdExist(id);

        UserCardInformation userCardInformation = this.userCardInformationDao.getById(id);
        UserCardInformationGetDto response = this.modelMapperService.forDto().map(userCardInformation,UserCardInformationGetDto.class);

        return new SuccessDataResult<>(response,BusinessMessages.DATA_BROUGHT + id);
    }

    @Override
    public Result add(CreateUserCardInformationRequest createUserCardInformationRequest, int customerId) {
        UserCardInformation userCardInformation = this.modelMapperService.forRequest().map(createUserCardInformationRequest, UserCardInformation.class);

        checkIfUserCardInformationsExists(userCardInformation, customerId);

        Customer customer = this.customerService.findById(customerId);

        userCardInformation.setCustomer(customer);

        this.userCardInformationDao.save(userCardInformation);

        return new SuccessResult(BusinessMessages.DATA_ADDED);
    }

    @Override
    public Result update(int id, UpdateUserCardInformationRequest updateUserCardInformationRequest) {
        checkIfIdExist(id);

        UserCardInformation userCardInformation = this.userCardInformationDao.getById(id);

        populateUserCardFields(userCardInformation, updateUserCardInformationRequest);

        this.userCardInformationDao.save(userCardInformation);

        return new SuccessResult(BusinessMessages.DATA_UPDATED + id);
    }

    @Override
    public Result delete(int id) {
        checkIfIdExist(id);

        this.userCardInformationDao.deleteById(id);

        return new SuccessResult(BusinessMessages.DATA_DELETED + id);
    }

    private void checkIfIdExist(int id) {
        if (!this.userCardInformationDao.existsById(id)) {
            throw new BusinessException(BusinessMessages.USER_CARD_DOES_NOT_EXISTS);
        }
    }

    private void populateUserCardFields(UserCardInformation userCardInformation, UpdateUserCardInformationRequest updateUserCardInformationRequest) {
        Customer customer = this.customerService.findById(updateUserCardInformationRequest.getCustomerId());

        userCardInformation.setCardNo(updateUserCardInformationRequest.getCardNo());
        userCardInformation.setCardHolder(updateUserCardInformationRequest.getCardHolder());
        userCardInformation.setCvv(updateUserCardInformationRequest.getCvv());
        userCardInformation.setExpireMonth(updateUserCardInformationRequest.getExpireMonth());
        userCardInformation.setExpireYear(updateUserCardInformationRequest.getExpireYear());
        userCardInformation.setCustomer(customer);
    }

    private void checkIfUserCardInformationsExists(UserCardInformation userCardInformation, int customerId) {
        List<UserCardInformation> customerCardInformations = this.userCardInformationDao.findByCustomerId(customerId);
        for (UserCardInformation customerCardInformation : customerCardInformations) {
            if (customerCardInformation.getCardNo().equals(userCardInformation.getCardNo())
                    && customerCardInformation.getCardHolder().equals(userCardInformation.getCardHolder())
                    && customerCardInformation.getCvv() == userCardInformation.getCvv()
                    && customerCardInformation.getExpireMonth() == userCardInformation.getExpireMonth()
                    && customerCardInformation.getExpireYear() == userCardInformation.getExpireYear()) {
                throw new BusinessException(BusinessMessages.USER_CARD_EXISTS);
            }
        }
    }
}
