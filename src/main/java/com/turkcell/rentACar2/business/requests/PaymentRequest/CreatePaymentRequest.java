package com.turkcell.rentACar2.business.requests.PaymentRequest;

import com.turkcell.rentACar2.business.requests.UserCardInformationRequest.CreateUserCardInformationRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {
    @NotNull
    private int rentalCarId;

    @Valid
    private CreateUserCardInformationRequest createUserCardInformationRequest;
}
