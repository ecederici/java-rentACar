package com.turkcell.rentACar2.business.abstracts;

import com.turkcell.rentACar2.entities.concretes.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getAll();
    void add(Brand brand);
}
