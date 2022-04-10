package com.turkcell.rentACar2.api.controllers;

import com.turkcell.rentACar2.business.abstracts.AdditionalServiceService;
import com.turkcell.rentACar2.business.dtos.AdditionalServiceDto.AdditionalServiceGetDto;
import com.turkcell.rentACar2.business.dtos.AdditionalServiceDto.AdditionalServiceListDto;
import com.turkcell.rentACar2.business.requests.AdditionalServiceRequest.CreateAdditionalServiceRequest;
import com.turkcell.rentACar2.business.requests.AdditionalServiceRequest.UpdateAdditionalServiceRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/additionalServices")
public class AdditionalServiceController {
    private final AdditionalServiceService additionalServiceService;

    public AdditionalServiceController(AdditionalServiceService additionalServiceService) {
        this.additionalServiceService = additionalServiceService;
    }

    @GetMapping("/getAll")
    public DataResult<List<AdditionalServiceListDto>> getAll() {
        return this.additionalServiceService.getAll();
    }

    @GetMapping("/getById/{id}")
    public DataResult<AdditionalServiceGetDto> getById(@PathVariable int id) {
        return this.additionalServiceService.getById(id);
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateAdditionalServiceRequest createAdditionalServiceRequest) {
        return this.additionalServiceService.add(createAdditionalServiceRequest);
    }

    @PutMapping("/update/{id}")
    public Result update(@PathVariable int id, @RequestBody @Valid UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
        return this.additionalServiceService.update(id, updateAdditionalServiceRequest);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id) {
        return this.additionalServiceService.delete(id);
    }
}
