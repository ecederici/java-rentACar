package com.turkcell.rentACar2.business.dtos.CarDto;

import com.turkcell.rentACar2.business.dtos.CarDamageDto.CarDamageListDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarGetDto {

    private double dailyPrice;

    private int modelYear;

    private String description;

    private String colorName;

    private String brandName;

    private int currentDistance;

    private List<CarDamageListDto> carDamages;
}
