package com.turkcell.rentACar2.api.controllers;

import com.turkcell.rentACar2.business.abstracts.CorporateCustomerService;
import com.turkcell.rentACar2.business.dtos.CorporateCustomerDto.CorporateCustomerGetDto;
import com.turkcell.rentACar2.business.dtos.CorporateCustomerDto.CorporateCustomerListDto;
import com.turkcell.rentACar2.business.requests.CorporateCustomerRequest.CreateCorporateCustomerRequest;
import com.turkcell.rentACar2.business.requests.CorporateCustomerRequest.UpdateCorporateCustomerRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/corporateCustomers")
@AllArgsConstructor
public class CorporateCustomersController {
    private final CorporateCustomerService corporateCustomerService;

    @GetMapping("/getAll")
    DataResult<List<CorporateCustomerListDto>> getAll() {
        return this.corporateCustomerService.getAll();
    }

    @GetMapping("/getById/{id}")
    DataResult<CorporateCustomerGetDto> getById(@PathVariable int id) {
        return this.corporateCustomerService.getById(id);
    }

    @PostMapping("/add")
    Result add(@RequestBody @Valid CreateCorporateCustomerRequest createCorporateCustomerRequest) {
        return this.corporateCustomerService.add(createCorporateCustomerRequest);
    }

    @PutMapping("/update/{id}")
    Result update(@PathVariable int id, @RequestBody @Valid UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
        return this.corporateCustomerService.update(id, updateCorporateCustomerRequest);
    }

    @DeleteMapping("/delete/{id}")
    Result delete(int id) {
        return this.corporateCustomerService.delete(id);
    }
}
