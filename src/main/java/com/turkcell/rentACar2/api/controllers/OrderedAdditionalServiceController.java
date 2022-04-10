package com.turkcell.rentACar2.api.controllers;

import com.turkcell.rentACar2.business.abstracts.OrderedAdditionalServiceService;
import com.turkcell.rentACar2.business.dtos.OrderedAdditionalServiceDto.OrderedAdditionalServiceGetDto;
import com.turkcell.rentACar2.business.dtos.OrderedAdditionalServiceDto.OrderedAdditionalServiceListDto;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderedAdditionalServices")
public class OrderedAdditionalServiceController {
    private final OrderedAdditionalServiceService orderedAdditionalServiceService;

    public OrderedAdditionalServiceController(OrderedAdditionalServiceService orderedAdditionalServiceService) {
        this.orderedAdditionalServiceService = orderedAdditionalServiceService;
    }

    @GetMapping("/getAll")
    public DataResult<List<OrderedAdditionalServiceListDto>> getAll() {
        return this.orderedAdditionalServiceService.getAll();
    }

    @GetMapping("/getById/{id}")
    public DataResult<OrderedAdditionalServiceGetDto> getById(@PathVariable int id) {
        return this.orderedAdditionalServiceService.getById(id);
    }

   // @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id) {
        return this.orderedAdditionalServiceService.delete(id);
    }

    @GetMapping("/getByRentalCarId/{rental_car_id}")
    public DataResult<List<OrderedAdditionalServiceListDto>> getByRentalCarId(@PathVariable("rental_car_id") int rentalCarId) {
        return this.orderedAdditionalServiceService.getByRentalCarId(rentalCarId);
    }

    @GetMapping("/getByAdditionalServiceId/{additional_service_id}")
    public DataResult<List<OrderedAdditionalServiceListDto>> getByAdditionalServiceId(@PathVariable("additional_service_id") int additionalServiceId) {
        return this.orderedAdditionalServiceService.getByAdditionalServiceId(additionalServiceId);
    }
}
