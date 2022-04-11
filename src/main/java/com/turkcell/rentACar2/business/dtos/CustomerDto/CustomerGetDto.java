package com.turkcell.rentACar2.business.dtos.CustomerDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerGetDto {
    private int id;
    private String email;
    private LocalDateTime registrationDate;
}
