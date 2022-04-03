package com.turkcell.rentACar2.business.concretes;

import com.turkcell.rentACar2.business.abstracts.BrandService;
import com.turkcell.rentACar2.business.constants.messages.BusinessMessages;
import com.turkcell.rentACar2.business.dtos.BrandDto.BrandGetDto;
import com.turkcell.rentACar2.business.dtos.BrandDto.BrandListDto;
import com.turkcell.rentACar2.business.requests.BrandRequest.CreateBrandRequest;
import com.turkcell.rentACar2.business.requests.BrandRequest.UpdateBrandRequest;
import com.turkcell.rentACar2.core.utilities.exception.BusinessException;
import com.turkcell.rentACar2.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import com.turkcell.rentACar2.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar2.core.utilities.results.SuccessResult;
import com.turkcell.rentACar2.dataAccess.abstracts.BrandDao;
import com.turkcell.rentACar2.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandDao brandDao;
    private final ModelMapperService modelMapperService;

    @Override
    public DataResult<List<BrandListDto>> getAll() {
        List<Brand> result = this.brandDao.findAll();

        List<BrandListDto> response = result.stream().map(brand -> this.modelMapperService.forDto().map(brand,BrandListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(response, BusinessMessages.DATA_LISTED);
    }

    @Override
    public Result add(CreateBrandRequest createBrandRequest) {

        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest,Brand.class);

        checkIfBrandNameExist(brand.getName());

        this.brandDao.save(brand);

        return new SuccessResult(BusinessMessages.DATA_ADDED);
    }

    @Override
    public DataResult<BrandGetDto> getById(int id) {
        checkIfBrandIdExist(id);

        Brand brand = this.brandDao.getById(id);
        BrandGetDto response = this.modelMapperService.forDto().map(brand, BrandGetDto.class);

        return new SuccessDataResult<>(response, BusinessMessages.DATA_BROUGHT + id);
    }

    @Override
    public Result update(int id, UpdateBrandRequest updateBrandRequest) {
       checkIfBrandIdExist(id);

       Brand brand = this.brandDao.getById(id);

       checkIfBrandNameExist(brand.getName());

       brand.setName(updateBrandRequest.getName());
       this.brandDao.save(brand);

       return new SuccessResult(BusinessMessages.DATA_UPDATED + id);
    }

    @Override
    public Result delete(int id) {
        checkIfBrandIdExist(id);

        this.brandDao.deleteById(id);

        return new SuccessResult(BusinessMessages.DATA_DELETED + id);
    }

    private void checkIfBrandIdExist(int id) {
        if (!this.brandDao.existsById(id)) {
            throw new BusinessException(BusinessMessages.BRAND_DOES_NOT_EXISTS);
        }
    }

    private void checkIfBrandNameExist(String name) {
        if (this.brandDao.existsByName(name)) {
            throw new BusinessException(name + BusinessMessages.BRAND_ALREADY_EXISTS);
        }
    }
}
