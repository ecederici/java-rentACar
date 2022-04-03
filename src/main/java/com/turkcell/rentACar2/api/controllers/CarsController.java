package com.turkcell.rentACar2.api.controllers;

import com.turkcell.rentACar2.business.abstracts.CarService;
import com.turkcell.rentACar2.business.dtos.CarDto.CarGetDto;
import com.turkcell.rentACar2.business.dtos.CarDto.CarListDto;
import com.turkcell.rentACar2.business.requests.CarRequest.CreateCarRequest;
import com.turkcell.rentACar2.business.requests.CarRequest.UpdateCarRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/cars")
@RestController
public class CarsController {
    private CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/getById/{id}")
    public @ResponseBody DataResult<CarGetDto> getById(@PathVariable int id) {
        return this.carService.getById(id);
    }

    @GetMapping("/getAll")
    public DataResult<List<CarListDto>> getAll() {
        return this.carService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) {
       return this.carService.add(createCarRequest);
    }

    @PutMapping("/update/{id}")
    public Result update(@PathVariable int id, @RequestBody @Valid UpdateCarRequest updateCarRequest) {
         return this.carService.update(id, updateCarRequest);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id) {
        return this.carService.delete(id);
    }

    @GetMapping("/getAllPaged")
    public DataResult<List<CarListDto>> getAllPaged(@RequestParam int pageNo, @RequestParam int pageSize) {
        return this.carService.getAllPaged(pageNo,pageSize);
    }

    @GetMapping("/getAllSorted")
    public DataResult<List<CarListDto>> getAllSorted(@RequestParam Sort.Direction sort) {
        return this.carService.getAllSorted(sort);
    }

    @GetMapping("/findByDailyPriceLessThanEqual")
    public DataResult<List<CarListDto>> findByDailyPriceLessThanEqual(@RequestParam double dailyPrice) {
        return this.carService.findByDailyPriceLessThanEqual(dailyPrice);
    }

    @GetMapping("/findByModelYear")
    public DataResult<List<CarListDto>> findByModelYear(@RequestParam int modelYear) {
        return this.carService.findCarsByModelYear(modelYear);
    }

    @GetMapping("/findByColorId")
    public DataResult<List<CarListDto>> findByColorId(@RequestParam int colorId) {
        return this.carService.findByColorId(colorId);
    }
}
