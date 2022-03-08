package com.turkcell.rentACar2.business.abstracts;

import com.turkcell.rentACar2.business.dtos.BrandGetDto;
import com.turkcell.rentACar2.business.dtos.BrandListDto;
import com.turkcell.rentACar2.entities.concretes.Brand;

import java.util.List;

public interface BrandService {
    List<BrandListDto> getAll();
    void add(Brand brand);
    BrandGetDto getById(int id);
}
