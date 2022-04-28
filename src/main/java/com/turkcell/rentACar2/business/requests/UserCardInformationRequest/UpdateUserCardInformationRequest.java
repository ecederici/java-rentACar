package com.turkcell.rentACar2.business.requests.UserCardInformationRequest;

import com.turkcell.rentACar2.business.constants.messages.BusinessMessages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserCardInformationRequest {

    @NotNull
    @Size(min = 16,max = 16, message = BusinessMessages.INVALID_CARD_NO)
    private String cardNo;

    @NotNull
    @Size(min = 2,max = 50, message = BusinessMessages.INVALID_CARD_HOLDER)
    private String cardHolder;

    @NotNull
    @Range(min = 1, max = 12, message = BusinessMessages.INVALID_EXPIRE_MONTH)
    private int expireMonth;

    @NotNull
    @Range(min = 2020, max = 2030, message = BusinessMessages.INVALID_EXPIRE_YEAR)
    private int expireYear;

    @NotNull
    @Range(min = 100, max = 999, message = BusinessMessages.INVALID_CVV)
    private int cvv;

    @NotNull
    private int customerId;
}
