package com.turkcell.rentACar2.api.controllers;

import com.turkcell.rentACar2.business.abstracts.InvoiceService;
import com.turkcell.rentACar2.business.dtos.InvoiceDto.InvoiceGetDto;
import com.turkcell.rentACar2.business.dtos.InvoiceDto.InvoiceListDto;
import com.turkcell.rentACar2.business.requests.InvoiceRequest.CreateInvoiceRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/getAll")
    DataResult<List<InvoiceListDto>> getAll() {
        return this.invoiceService.getAll();
    }

    @GetMapping("/getById/{id}")
    DataResult<InvoiceGetDto> getById(@PathVariable int id) {
        return this.invoiceService.getById(id);
    }

    @PostMapping("/add")
    Result add(@RequestBody @Valid CreateInvoiceRequest createInvoiceRequest) {
        return this.invoiceService.add(createInvoiceRequest);
    }

    @DeleteMapping("/delete/{id}")
    Result delete(@PathVariable int id) {
        return this.invoiceService.delete(id);
    }

    @GetMapping("/getAllByInvoiceDate")
    DataResult<List<InvoiceListDto>> getAllByInvoiceDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return this.invoiceService.getByInvoiceDate(startDate, endDate);
    }

    @GetMapping("/getAllByCustomerId")
    DataResult<List<InvoiceListDto>> getAllByCustomerId(@RequestParam int customerId) {
        return this.invoiceService.findAllByCustomerId(customerId);
    }

}
