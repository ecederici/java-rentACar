package com.turkcell.rentACar2.business.dtos.IndividualCustomerDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerGetDto {

    private String firstName;
    private String lastName;
    private String nationalIdentity;
    private LocalDateTime registrationDate;
}
