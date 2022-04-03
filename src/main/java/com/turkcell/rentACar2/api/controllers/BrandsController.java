package com.turkcell.rentACar2.api.controllers;

import com.turkcell.rentACar2.business.abstracts.BrandService;
import com.turkcell.rentACar2.business.dtos.BrandDto.BrandGetDto;
import com.turkcell.rentACar2.business.dtos.BrandDto.BrandListDto;
import com.turkcell.rentACar2.business.requests.BrandRequest.CreateBrandRequest;
import com.turkcell.rentACar2.business.requests.BrandRequest.UpdateBrandRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {
    private final BrandService brandService;

    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/getAll")
    public DataResult<List<BrandListDto>> getAll() {
        return this.brandService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateBrandRequest createBrandRequest) {
       return this.brandService.add(createBrandRequest);
    }

    @GetMapping("/getById/{id}")
    public DataResult<BrandGetDto> getById(@PathVariable int id) {
        return this.brandService.getById(id);
    }

    @PutMapping("/update/{id}")
    public Result update(@PathVariable int id, @RequestBody @Valid UpdateBrandRequest updateBrandRequest) {
        return this.brandService.update(id, updateBrandRequest);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id) {
        return this.brandService.delete(id);
    }
}
