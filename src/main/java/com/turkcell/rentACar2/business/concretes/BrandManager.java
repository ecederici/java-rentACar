package com.turkcell.rentACar2.business.concretes;

import com.turkcell.rentACar2.business.abstracts.BrandService;
import com.turkcell.rentACar2.dataAccess.abstracts.BrandDao;
import com.turkcell.rentACar2.entities.concretes.Brand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandManager implements BrandService {
    private BrandDao brandDao;

    public BrandManager(BrandDao brandDao) {
        this.brandDao = brandDao;
    }
    @Override
    public List<Brand> getAll() {
        return this.brandDao.findAll();
    }

    @Override
    public void add(Brand brand) {
        if(this.brandDao.findByBrandName(brand.getBrandName()) != null) {
            System.out.println("hata");
        } else {
            this.brandDao.save(brand);
        }
    }
}
