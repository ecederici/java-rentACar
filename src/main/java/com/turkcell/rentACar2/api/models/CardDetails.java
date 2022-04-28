package com.turkcell.rentACar2.api.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDetails {

    private String cardNo;
    private String cardHolder;
    private int expireMonth;
    private int expireYear;
    private int cvv;
}
