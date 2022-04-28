package com.turkcell.rentACar2.business.abstracts;

import com.turkcell.rentACar2.business.dtos.UserCardInformationDto.UserCardInformationGetDto;
import com.turkcell.rentACar2.business.dtos.UserCardInformationDto.UserCardInformationListDto;
import com.turkcell.rentACar2.business.requests.UserCardInformationRequest.CreateUserCardInformationRequest;
import com.turkcell.rentACar2.business.requests.UserCardInformationRequest.UpdateUserCardInformationRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;

import java.util.List;

public interface UserCardInformationService {
    Result add(CreateUserCardInformationRequest createUserCardInformationRequest, int customerId);

    DataResult<List<UserCardInformationListDto>> getAll();

    DataResult<UserCardInformationGetDto> getById(int id);

    Result update(int id, UpdateUserCardInformationRequest updateUserCardInformationRequest);

    Result delete(int id);
}
