package com.turkcell.rentACar2.business.dtos.AdditionalServiceDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalServiceListDto {
    private String name;
    private double dailyPrice;
}
