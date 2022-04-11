package com.turkcell.rentACar2.business.dtos.CorporateCustomerDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateCustomerListDto {

    private String companyName;
    private String taxNumber;
    private LocalDateTime registrationDate;
}
