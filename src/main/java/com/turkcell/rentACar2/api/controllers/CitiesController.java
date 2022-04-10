package com.turkcell.rentACar2.api.controllers;

import com.turkcell.rentACar2.business.abstracts.CityService;
import com.turkcell.rentACar2.business.dtos.CityDto.CityGetDto;
import com.turkcell.rentACar2.business.dtos.CityDto.CityListDto;
import com.turkcell.rentACar2.business.requests.CityRequest.CreateCityRequest;
import com.turkcell.rentACar2.business.requests.CityRequest.UpdateCityRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
    private final CityService cityService;

    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/getAll")
    DataResult<List<CityListDto>> getAll() {
        return this.cityService.getAll();
    }

    @GetMapping("/getById/{id}")
    DataResult<CityGetDto> getById(@PathVariable int id) {
        return this.cityService.getById(id);
    }

    @PostMapping("/add")
    Result add(@RequestBody @Valid CreateCityRequest createCityRequest) {
        return this.cityService.add(createCityRequest);
    }

    @PutMapping("/update/{id}")
    Result update(@PathVariable int id, @RequestBody @Valid UpdateCityRequest updateCityRequest) {
        return this.cityService.update(id, updateCityRequest);
    }

    @DeleteMapping("/delete/{id}")
    Result delete(@PathVariable int id) {
        return this.cityService.delete(id);
    }

}
