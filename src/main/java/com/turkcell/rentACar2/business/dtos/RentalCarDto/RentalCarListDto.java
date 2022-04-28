package com.turkcell.rentACar2.business.dtos.RentalCarDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCarListDto {

    private LocalDate rentDate;
    private LocalDate returnDate;
    private LocalDate executionDate;
    private int customerId;
    private int carId;
    private String fromCityName;
    private String toCityName;
    private int currentDistance;
    private int lastDistance;
}
