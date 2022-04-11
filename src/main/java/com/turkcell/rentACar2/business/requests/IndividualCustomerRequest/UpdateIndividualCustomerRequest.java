package com.turkcell.rentACar2.business.requests.IndividualCustomerRequest;

import com.turkcell.rentACar2.business.constants.messages.BusinessMessages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest {

    @NotNull
    @Size(min = 2, message = BusinessMessages.INVALID_FIRST_NAME)
    private String firstName;

    @NotNull
    @Size(min = 2, message = BusinessMessages.INVALID_LAST_NAME)
    private String lastName;

    @NotNull
    @Size(min=11, max=11, message = BusinessMessages.INVALID_NATIONAL_IDENTITY_NUMBER)
    private String nationalIdentity;

    @NotNull
    @Email
    private String Email;

    @NotNull
    @Size(min = 6, message = BusinessMessages.INVALID_PASSWORD)
    private String password;
}
