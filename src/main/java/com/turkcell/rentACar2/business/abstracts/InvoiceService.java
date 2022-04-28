package com.turkcell.rentACar2.business.abstracts;

import com.turkcell.rentACar2.business.dtos.InvoiceDto.InvoiceGetDto;
import com.turkcell.rentACar2.business.dtos.InvoiceDto.InvoiceListDto;
import com.turkcell.rentACar2.business.requests.InvoiceRequest.CreateInvoiceRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import com.turkcell.rentACar2.entities.concretes.Invoice;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceService {
    DataResult<List<InvoiceListDto>> getAll();

    DataResult<InvoiceGetDto> getById(int id);

    DataResult<Invoice> add(CreateInvoiceRequest createInvoiceRequest);

    Result delete(int id);

    DataResult<List<InvoiceListDto>> getByInvoiceDate(LocalDate startDate, LocalDate endDate);

    DataResult<List<InvoiceListDto>> findAllByCustomerId(int customerId);

    DataResult<Invoice> addForDelay(CreateInvoiceRequest createInvoiceRequest);
}
