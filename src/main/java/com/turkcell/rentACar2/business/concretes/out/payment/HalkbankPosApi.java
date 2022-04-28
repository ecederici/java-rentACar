package com.turkcell.rentACar2.business.concretes.out.payment;

/* Bu class, Halk bankasının ödeme yapmak için kullandığı metodu içeren ve bizim dokunamadığımız
 bir class'ı ifade ediyor. Bu class'ı projemize adapte edeceğiz.*/
public class HalkbankPosApi {

    public boolean pay(String cardNo, String cardHolder, int expireMonth, int expireYear, int cvv, double paymentAmount) {
        return true;
    }
}
