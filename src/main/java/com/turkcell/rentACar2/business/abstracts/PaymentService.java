package com.turkcell.rentACar2.business.abstracts;

import com.turkcell.rentACar2.api.models.PaymentModel;
import com.turkcell.rentACar2.api.models.ReturnedRentalCarModel;
import com.turkcell.rentACar2.business.dtos.PaymentDto.PaymentGetDto;
import com.turkcell.rentACar2.business.dtos.PaymentDto.PaymentListDto;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;

import java.util.List;

public interface PaymentService {
    DataResult<List<PaymentListDto>> getAll();

    DataResult<PaymentGetDto> getById(int id);

    Result add(PaymentModel paymentModel);

    Result delete(int id);

    void createPaymentForDelay(int id, ReturnedRentalCarModel returnedRentalCarModel);
}
