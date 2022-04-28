package com.turkcell.rentACar2.api.models;

import com.turkcell.rentACar2.business.requests.RentalCarRequest.UpdateReturnedRentalCarRequest;
import com.turkcell.rentACar2.business.requests.UserCardInformationRequest.CreateUserCardInformationRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnedRentalCarModel {
    @Valid
    UpdateReturnedRentalCarRequest updateReturnedRentalCarRequest;

    @Valid
    CreateUserCardInformationRequest createUserCardInformationRequest;
}
