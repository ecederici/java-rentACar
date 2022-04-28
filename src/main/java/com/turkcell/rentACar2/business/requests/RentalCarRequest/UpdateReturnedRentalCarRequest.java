package com.turkcell.rentACar2.business.requests.RentalCarRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReturnedRentalCarRequest {

    @Min(1)
    @NotNull
    private int lastDistance;
}
