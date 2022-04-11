package com.turkcell.rentACar2.api.controllers;

import com.turkcell.rentACar2.business.abstracts.CustomerService;
import com.turkcell.rentACar2.business.dtos.CustomerDto.CustomerGetDto;
import com.turkcell.rentACar2.business.dtos.CustomerDto.CustomerListDto;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomersController {
    private final CustomerService customerService;

    @GetMapping("/getAll")
    DataResult<List<CustomerListDto>> getAll() {
        return this.customerService.getAll();
    }

    @GetMapping("/getById/{id}")
    DataResult<CustomerGetDto> getById(@PathVariable int id) {
        return this.customerService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    Result delete(@PathVariable int id) {
        return this.customerService.delete(id);
    }
}
