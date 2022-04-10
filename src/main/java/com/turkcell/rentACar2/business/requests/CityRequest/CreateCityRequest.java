package com.turkcell.rentACar2.business.requests.CityRequest;

import com.turkcell.rentACar2.business.constants.messages.BusinessMessages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCityRequest {

    @NotNull
    @Size(min = 3, message = BusinessMessages.INVALID_CITY_NAME)
    private String name;
}
