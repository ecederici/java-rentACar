package com.turkcell.rentACar2.business.dtos.CarMaintenanceDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintenanceListDto {
    private String description;
    private LocalDate returnDate;
    private int carId;
}
