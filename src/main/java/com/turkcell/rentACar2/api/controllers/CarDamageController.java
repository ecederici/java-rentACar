package com.turkcell.rentACar2.api.controllers;

import com.turkcell.rentACar2.business.abstracts.CarDamageService;
import com.turkcell.rentACar2.business.dtos.CarDamageDto.CarDamageGetDto;
import com.turkcell.rentACar2.business.dtos.CarDamageDto.CarDamageListDto;
import com.turkcell.rentACar2.business.requests.CarDamageRequest.CreateCarDamageRequest;
import com.turkcell.rentACar2.business.requests.CarDamageRequest.UpdateCarDamageRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/carDamages")
public class CarDamageController {
    private final CarDamageService carDamageService;

    public CarDamageController(CarDamageService carDamageService) {
        this.carDamageService = carDamageService;
    }

    @GetMapping("/getById/{id}")
    public @ResponseBody
    DataResult<CarDamageGetDto> getById(@PathVariable int id) {
        return this.carDamageService.getById(id);
    }

    @GetMapping("/getAll")
    public DataResult<List<CarDamageListDto>> getAll() {
        return this.carDamageService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateCarDamageRequest createCarDamageRequest) {
        return this.carDamageService.add(createCarDamageRequest);
    }

    @PutMapping("/update/{id}")
    public Result update(@PathVariable int id, @RequestBody @Valid UpdateCarDamageRequest updateCarDamageRequest) {
        return this.carDamageService.update(id, updateCarDamageRequest);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id) {
        return this.carDamageService.delete(id);
    }

    @GetMapping("/getAllByCarId")
    public DataResult<List<CarDamageListDto>> getByCarId(@RequestParam int carId) {
        return this.carDamageService.getByCarId(carId);
    }
}
