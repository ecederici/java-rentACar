package com.turkcell.rentACar2.business.adapters.payment;

import com.turkcell.rentACar2.api.models.CardDetails;
import com.turkcell.rentACar2.business.abstracts.PosService;
import com.turkcell.rentACar2.business.concretes.out.payment.HalkbankPosApi;
import org.springframework.stereotype.Service;

@Service
public class HalkbankApiAdapter implements PosService {

    @Override
    public boolean makePayment(CardDetails cardDetails, double paymentAmount) {
        HalkbankPosApi halkbankPosApi = new HalkbankPosApi();

        return halkbankPosApi.pay(
                cardDetails.getCardNo(),
                cardDetails.getCardHolder(),
                cardDetails.getExpireMonth(),
                cardDetails.getExpireYear(),
                cardDetails.getCvv(),
                paymentAmount
        );
    }
}
