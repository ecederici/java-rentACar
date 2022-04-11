package com.turkcell.rentACar2.business.requests.CorporateCustomerRequest;

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
public class UpdateCorporateCustomerRequest {

    @NotNull
    @Size(min = 3, message = BusinessMessages.INVALID_COMPANY_NAME)
    private String companyName;

    @NotNull
    @Size(min = 10, max = 10, message = BusinessMessages.INVALID_TAX_NUMBER)
    private String taxNumber;

    @NotNull
    @Email
    private String Email;

    @NotNull
    @Size(min = 6, message = BusinessMessages.INVALID_PASSWORD)
    private String password;
}
