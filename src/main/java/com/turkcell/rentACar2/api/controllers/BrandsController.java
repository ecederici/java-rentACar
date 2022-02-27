package com.turkcell.rentACar2.api.controllers;

import com.turkcell.rentACar2.business.abstracts.BrandService;
import com.turkcell.rentACar2.entities.concretes.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {
    private BrandService brandService;

    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/getall")
    public List<Brand> getAll() {
        return this.brandService.getAll();
    }

    @PostMapping("/add")
    public void add(Brand brand) {
        this.brandService.add(brand);
    }
}
