package com.turkcell.rentACar2.business.dtos.InvoiceDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceListDto {

    private int invoiceNumber;
    private LocalDate invoiceDate;
    private double totalPayment;
    private int totalRentalDays;
    private int rentalCarId;
}
