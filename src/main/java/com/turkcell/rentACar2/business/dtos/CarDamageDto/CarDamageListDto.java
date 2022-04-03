package com.turkcell.rentACar2.business.dtos.CarDamageDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDamageListDto {
    private String description;
    private int carId;
    private LocalDate executionDate;
}
