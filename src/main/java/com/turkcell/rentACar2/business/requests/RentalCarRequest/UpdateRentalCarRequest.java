package com.turkcell.rentACar2.business.requests.RentalCarRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalCarRequest {

    @NotNull
    private LocalDate rentDate;

    @NotNull
    private LocalDate returnDate;

    @NotNull
    private int carId;

    @NotNull
    private int fromCityId;

    @NotNull
    private int toCityId;
}
