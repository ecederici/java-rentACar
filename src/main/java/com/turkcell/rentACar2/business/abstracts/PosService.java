package com.turkcell.rentACar2.business.abstracts;

import com.turkcell.rentACar2.api.models.CardDetails;

public interface PosService {
    boolean makePayment(CardDetails cardDetails, double paymentAmount);
}
