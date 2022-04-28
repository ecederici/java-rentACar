package com.turkcell.rentACar2.api.controllers;

import com.turkcell.rentACar2.api.models.ReturnedRentalCarModel;
import com.turkcell.rentACar2.business.abstracts.RentalCarService;
import com.turkcell.rentACar2.business.dtos.RentalCarDto.RentalCarGetDto;
import com.turkcell.rentACar2.business.dtos.RentalCarDto.RentalCarListDto;
import com.turkcell.rentACar2.business.requests.RentalCarRequest.UpdateRentalCarRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/rentalCars")
public class RentalCarsController {

    private final RentalCarService rentalCarService;

    @Autowired
    public RentalCarsController(RentalCarService rentalCarService) {
        this.rentalCarService = rentalCarService;
    }

    @GetMapping("/getAll")
    public DataResult<List<RentalCarListDto>> getAll() {
        return this.rentalCarService.getAll();
    }

    @GetMapping("/getById/{id}")
    public DataResult<RentalCarGetDto> getById(@PathVariable int id) {
        return this.rentalCarService.getById(id);
    }

    @PutMapping("/updateForReturnFromRental/{id}")
    public Result updateForReturnFromRental(@PathVariable int id, @RequestBody @Valid ReturnedRentalCarModel returnedRentalCarModel) {
        return this.rentalCarService.updateForReturnFromRental(id, returnedRentalCarModel);
    }

    @PutMapping("/update/{id}")
    public Result update(@PathVariable int id, @RequestBody @Valid UpdateRentalCarRequest updateRentalCarRequest) {
        return this.rentalCarService.update(id, updateRentalCarRequest);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id) {
        return this.rentalCarService.delete(id);
    }
}
