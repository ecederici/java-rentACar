package com.turkcell.rentACar2.business.dtos.BrandDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandGetDto {

    private int id;
    private String name;
}
