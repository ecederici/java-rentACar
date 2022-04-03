package com.turkcell.rentACar2.business.dtos.CarDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarListDto {

    private double dailyPrice;

    private int modelYear;

    private String description;

    private String colorName;

    private String brandName;

    private int currentDistance;
}
