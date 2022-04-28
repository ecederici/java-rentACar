package com.turkcell.rentACar2.business.dtos.UserCardInformationDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCardInformationListDto {
    private String cardNo;

    private String cardHolder;

    private int expireMonth;

    private int expireYear;

    private int cvv;
}
