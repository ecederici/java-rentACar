package com.turkcell.rentACar2.api.controllers;

import com.turkcell.rentACar2.business.abstracts.PaymentService;
import com.turkcell.rentACar2.business.dtos.PaymentDto.PaymentGetDto;
import com.turkcell.rentACar2.business.dtos.PaymentDto.PaymentListDto;
import com.turkcell.rentACar2.api.models.PaymentModel;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class PaymentsController {
    private PaymentService paymentService;

    @GetMapping("/getAll")
    public DataResult<List<PaymentListDto>> getAll() {
        return this.paymentService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid PaymentModel paymentModel) {
        return this.paymentService.add(paymentModel);
    }

    @GetMapping("/getById/{id}")
    public DataResult<PaymentGetDto> getById(@PathVariable int id) {
        return this.paymentService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id) {
        return this.paymentService.delete(id);
    }
}
