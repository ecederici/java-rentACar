package com.turkcell.rentACar2.business.requests.ColorRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateColorRequest {

    @NotNull
    @Size(min = 3, max = 20)
    private String name;
}
