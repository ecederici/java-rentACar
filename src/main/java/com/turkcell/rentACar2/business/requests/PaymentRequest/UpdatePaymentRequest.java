package com.turkcell.rentACar2.business.requests.PaymentRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentRequest {
    @NotNull
    private int rentalCarId;

    @NotNull
    private String cardNo;

    @NotNull
    private String cardHolder;

    @NotNull
    private int expireMonth;

    @NotNull
    private int expireYear;

    @NotNull
    private int cvv;
}
