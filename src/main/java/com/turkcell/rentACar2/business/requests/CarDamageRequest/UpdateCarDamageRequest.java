package com.turkcell.rentACar2.business.requests.CarDamageRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarDamageRequest {
    @NotNull
    @Size(min = 5)
    private String description;

    @NotNull
    private LocalDate executionDate;
}
