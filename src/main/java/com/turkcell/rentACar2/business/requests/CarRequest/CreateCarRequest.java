package com.turkcell.rentACar2.business.requests.CarRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateCarRequest {

    @NotNull
    @Min(value = 1,message = "Daily price cannot be less than 1")
    private double dailyPrice;

    @NotNull
    @Min(value = 1930,message = "Model year cannot be less than 1930")
    private int modelYear;

    @NotNull
    @Size(min = 3)
    private String description;

    @NotNull
    @Min(1)
    private int colorId;

    @NotNull
    @Min(1)
    private int brandId;

    @NotNull
    @Min(0)
    private int currentDistance;

}
