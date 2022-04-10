package com.turkcell.rentACar2.business.requests.AdditionalServiceRequest;

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
public class UpdateAdditionalServiceRequest {

    @NotNull
    @Size(min = 2, message = BusinessMessages.INVALID_ADDITIONAL_SERVICE_NAME)
    private String name;

    @NotNull
    @Min(value = 1, message = BusinessMessages.INVALID_DAILY_PRICE)
    private double dailyPrice;
}
