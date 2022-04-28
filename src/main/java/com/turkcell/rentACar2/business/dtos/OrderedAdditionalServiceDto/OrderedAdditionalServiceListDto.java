package com.turkcell.rentACar2.business.dtos.OrderedAdditionalServiceDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedAdditionalServiceListDto {
    private int additionalServiceId;

    private String additionalServiceName;

    private int quantity;

    private int rentalCarId;
}
