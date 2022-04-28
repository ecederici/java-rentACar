package com.turkcell.rentACar2.business.concretes;

import com.turkcell.rentACar2.business.abstracts.CityService;
import com.turkcell.rentACar2.business.constants.messages.BusinessMessages;
import com.turkcell.rentACar2.business.dtos.CityDto.CityGetDto;
import com.turkcell.rentACar2.business.dtos.CityDto.CityListDto;
import com.turkcell.rentACar2.business.requests.CityRequest.CreateCityRequest;
import com.turkcell.rentACar2.business.requests.CityRequest.UpdateCityRequest;
import com.turkcell.rentACar2.core.utilities.exception.BusinessException;
import com.turkcell.rentACar2.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import com.turkcell.rentACar2.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar2.core.utilities.results.SuccessResult;
import com.turkcell.rentACar2.dataAccess.abstracts.CityDao;
import com.turkcell.rentACar2.entities.concretes.City;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class CityManager implements CityService {
    private final CityDao cityDao;
    private final ModelMapperService modelMapperService;

    public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
        this.cityDao = cityDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<CityListDto>> getAll() {
        List<City> cities = this.cityDao.findAll();

        List<CityListDto> response = cities.stream().map(city -> this.modelMapperService.forDto().map(city, CityListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(response, BusinessMessages.DATA_LISTED);

    }

    @Override
    public DataResult<CityGetDto> getById(int id) {
        checkIfCityIdExists(id);

        City city = this.cityDao.getById(id);

        CityGetDto cityGetDto = this.modelMapperService.forDto().map(city, CityGetDto.class);

        return new SuccessDataResult<>(cityGetDto, BusinessMessages.DATA_BROUGHT + id);
    }

    @Override
    public Result add(CreateCityRequest createCityRequest) {
        City city = this.modelMapperService.forRequest().map(createCityRequest, City.class);

        this.checkIfCityNameExists(city.getName());

        city.setName(createCityRequest.getName().toUpperCase(Locale.ROOT));
        this.cityDao.save(city);

        return new SuccessResult(BusinessMessages.DATA_ADDED);
    }

    @Override
    public Result update(int id, UpdateCityRequest updateCityRequest) {
        checkIfCityIdExists(id);
        checkIfCityNameExists(updateCityRequest.getName());

        City city = this.cityDao.getById(id);

        this.populateCityName(city,updateCityRequest);
        this.cityDao.save(city);

        return new SuccessResult(BusinessMessages.DATA_UPDATED + id);
    }

    @Override
    public Result delete(int id) {
        checkIfCityIdExists(id);

        this.cityDao.deleteById(id);

        return new SuccessResult(BusinessMessages.DATA_DELETED + id);
    }

    @Override
    public void checkIfCityIdExists(int id) {
        if (!this.cityDao.existsById(id)) {
            throw new BusinessException(BusinessMessages.CITY_DOES_NOT_EXISTS);
        }
    }

    @Override
    public City findById(int id) {
        return this.cityDao.getById(id);
    }

    public void populateCityName(City city, UpdateCityRequest updateCityRequest) {
        city.setName(updateCityRequest.getName().toUpperCase(Locale.ROOT));
    }

    private void checkIfCityNameExists(String name) {
        if (this.cityDao.existsByName(name.toUpperCase(Locale.ROOT))) {
            throw new BusinessException(BusinessMessages.CITY_ALREADY_EXISTS + name);
        }
    }
}
