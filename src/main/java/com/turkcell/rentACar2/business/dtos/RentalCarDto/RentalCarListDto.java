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
    private int carId;
    private int customerId;
    private String fromCity;
    private String toCity;
    private int currentDistance;
    private int lastDistance;
}
