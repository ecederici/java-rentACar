package com.turkcell.rentACar2.business.concretes;

import com.turkcell.rentACar2.business.abstracts.BrandService;
import com.turkcell.rentACar2.business.dtos.BrandGetDto;
import com.turkcell.rentACar2.business.dtos.BrandListDto;
import com.turkcell.rentACar2.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar2.dataAccess.abstracts.BrandDao;
import com.turkcell.rentACar2.entities.concretes.Brand;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandManager implements BrandService {
    private BrandDao brandDao;
    private ModelMapperService modelMapperService;

    public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
        this.brandDao = brandDao;
        this.modelMapperService = modelMapperService;
    }
    @Override
    public List<BrandListDto> getAll() {
        List<Brand> result = this.brandDao.findAll();
        List<BrandListDto> response = result.stream().map(brand -> this.modelMapperService.forDto().map(brand,BrandListDto.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public void add(Brand brand) {
        if(this.brandDao.findByBrandName(brand.getBrandName()) != null) {
            System.out.println("hata");
        } else {
            this.brandDao.save(brand);
        }
    }

    @Override
    public BrandGetDto getById(int id) {
        Brand brand = this.brandDao.getById(id);
        BrandGetDto response = this.modelMapperService.forDto().map(brand, BrandGetDto.class);
        return response;
    }
}
