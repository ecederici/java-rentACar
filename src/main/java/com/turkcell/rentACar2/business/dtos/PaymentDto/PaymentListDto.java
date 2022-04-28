package com.turkcell.rentACar2.business.dtos.PaymentDto;

import com.turkcell.rentACar2.entities.concretes.RentalCar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentListDto {

    private String cardNo;
    private String cardHolder;
    private int expireMonth;
    private int expireYear;
    private int cvv;
    private double paymentAmount;
    private RentalCar rentalCar;
}
