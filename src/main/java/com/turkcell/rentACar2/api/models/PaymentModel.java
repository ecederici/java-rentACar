package com.turkcell.rentACar2.api.models;

import com.turkcell.rentACar2.business.requests.UserCardInformationRequest.CreateUserCardInformationRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentModel {
    @Valid
    RentalCarModel rentalCarModel;

    @Valid
    CreateUserCardInformationRequest createUserCardInformationRequest;

    @NotNull
    boolean saveCard;
}
