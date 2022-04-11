package com.turkcell.rentACar2.api.controllers;

import com.turkcell.rentACar2.business.abstracts.CarMaintenanceService;
import com.turkcell.rentACar2.business.dtos.CarMaintenanceDto.CarMaintenanceGetDto;
import com.turkcell.rentACar2.business.dtos.CarMaintenanceDto.CarMaintenanceListDto;
import com.turkcell.rentACar2.business.requests.CarMaintenanceRequest.CreateCarMaintenanceRequest;
import com.turkcell.rentACar2.business.requests.CarMaintenanceRequest.UpdateCarMaintenanceRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/carMaintenances")
@AllArgsConstructor
public class CarMaintenancesController {
    private final CarMaintenanceService carMaintenanceService;

    @GetMapping("/getAll")
    public DataResult<List<CarMaintenanceListDto>> getAll() {
        return this.carMaintenanceService.getAll();
    }

    @GetMapping("/getById/{id}")
    public DataResult<CarMaintenanceGetDto> getById(@PathVariable int id) {
        return this.carMaintenanceService.getById(id);
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateCarMaintenanceRequest createCarMaintenanceRequest) {
        return this.carMaintenanceService.add(createCarMaintenanceRequest);
    }

    @PutMapping("/update/{id}")
    public Result update(@PathVariable int id, @RequestBody @Valid UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
        return this.carMaintenanceService.update(id, updateCarMaintenanceRequest);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id) {
        return this.carMaintenanceService.delete(id);
    }

    @GetMapping("/getByCarId")
    public DataResult<List<CarMaintenanceListDto>> getByCarId(@RequestParam int carId) {
        return this.carMaintenanceService.getByCarId(carId);
    }
}
