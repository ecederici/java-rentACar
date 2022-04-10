package com.turkcell.rentACar2.business.requests.CarRequest;

import com.turkcell.rentACar2.business.constants.messages.BusinessMessages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {

    @NotNull
    @Min(value = 1, message = BusinessMessages.INVALID_DAILY_PRICE)
    private double dailyPrice;

    @NotNull
    @Size(min = 3)
    private String description;

    @NotNull
    @Min(0)
    private int currentDistance;
}
