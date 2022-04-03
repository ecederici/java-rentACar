package com.turkcell.rentACar2.business.abstracts;

import com.turkcell.rentACar2.business.dtos.BrandDto.BrandGetDto;
import com.turkcell.rentACar2.business.dtos.BrandDto.BrandListDto;
import com.turkcell.rentACar2.business.requests.BrandRequest.CreateBrandRequest;
import com.turkcell.rentACar2.business.requests.BrandRequest.UpdateBrandRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;

import java.util.List;

public interface BrandService {
    DataResult<List<BrandListDto>> getAll();

    Result add(CreateBrandRequest createBrandRequest);

    DataResult<BrandGetDto> getById(int id);

    Result update(int id, UpdateBrandRequest updateBrandRequest);

    Result delete(int id);
}
