package com.turkcell.rentACar2.business.requests.CarMaintenanceRequest;

import com.turkcell.rentACar2.business.constants.messages.BusinessMessages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest {

    @NotNull
    @Size(min = 5, message = BusinessMessages.INVALID_CAR_MAINTENANCE_DESCRIPTION)
    private String description;

    private LocalDate returnDate;

    @NotNull
    private int carId;
}
