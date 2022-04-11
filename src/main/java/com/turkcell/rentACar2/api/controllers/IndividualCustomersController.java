package com.turkcell.rentACar2.api.controllers;

import com.turkcell.rentACar2.business.abstracts.IndividualCustomerService;
import com.turkcell.rentACar2.business.dtos.IndividualCustomerDto.IndividualCustomerGetDto;
import com.turkcell.rentACar2.business.dtos.IndividualCustomerDto.IndividualCustomerListDto;
import com.turkcell.rentACar2.business.requests.IndividualCustomerRequest.CreateIndividualCustomerRequest;
import com.turkcell.rentACar2.business.requests.IndividualCustomerRequest.UpdateIndividualCustomerRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/individualCustomers")
@AllArgsConstructor
public class IndividualCustomersController {
    private IndividualCustomerService individualCustomerService;

    @GetMapping("/getAll")
    DataResult<List<IndividualCustomerListDto>> getAll() {
        return this.individualCustomerService.getAll();
    }

    @GetMapping("/getById/{id}")
    DataResult<IndividualCustomerGetDto> getById(@PathVariable int id) {
        return this.individualCustomerService.getById(id);
    }

    @PostMapping("/add")
    Result add(@RequestBody @Valid CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        return this.individualCustomerService.add(createIndividualCustomerRequest);
    }

    @PutMapping("/update/{id}")
    Result update(@PathVariable int id, @RequestBody @Valid UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
        return this.individualCustomerService.update(id, updateIndividualCustomerRequest);
    }

    @DeleteMapping("/delete/{id}")
    Result delete(int id) {
        return this.individualCustomerService.delete(id);
    }
}
