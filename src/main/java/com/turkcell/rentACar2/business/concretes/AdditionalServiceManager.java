package com.turkcell.rentACar2.business.concretes;

import com.turkcell.rentACar2.business.abstracts.AdditionalServiceService;
import com.turkcell.rentACar2.business.constants.messages.BusinessMessages;
import com.turkcell.rentACar2.business.dtos.AdditionalServiceDto.AdditionalServiceGetDto;
import com.turkcell.rentACar2.business.dtos.AdditionalServiceDto.AdditionalServiceListDto;
import com.turkcell.rentACar2.business.requests.AdditionalServiceRequest.CreateAdditionalServiceRequest;
import com.turkcell.rentACar2.business.requests.AdditionalServiceRequest.UpdateAdditionalServiceRequest;
import com.turkcell.rentACar2.core.utilities.exception.BusinessException;
import com.turkcell.rentACar2.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import com.turkcell.rentACar2.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar2.core.utilities.results.SuccessResult;
import com.turkcell.rentACar2.dataAccess.abstracts.AdditionalServiceDao;
import com.turkcell.rentACar2.entities.concretes.AdditionalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {

    private final AdditionalServiceDao additionalServiceDao;
    private final ModelMapperService modelMapperService;

    public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao, ModelMapperService modelMapperService) {
        this.additionalServiceDao = additionalServiceDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<AdditionalServiceListDto>> getAll() {
        List<AdditionalService> additionalServices = this.additionalServiceDao.findAll();

        List<AdditionalServiceListDto> additionalServiceListDto = additionalServices.stream().map(additionalService -> this.modelMapperService.forDto().map(additionalService,AdditionalServiceListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(additionalServiceListDto, BusinessMessages.DATA_LISTED);
    }

    @Override
    public DataResult<AdditionalServiceGetDto> getById(int id) {
        checkIfIdExists(id);

        AdditionalService additionalService = this.additionalServiceDao.getById(id);

        AdditionalServiceGetDto additionalServiceGetDto = this.modelMapperService.forDto().map(additionalService, AdditionalServiceGetDto.class);

        return new SuccessDataResult<>(additionalServiceGetDto, BusinessMessages.DATA_BROUGHT + id);
    }

    @Override
    public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
        checkIfNameExists(createAdditionalServiceRequest.getName());

        AdditionalService additionalService = this.modelMapperService.forRequest().map(createAdditionalServiceRequest, AdditionalService.class);

        additionalService.setName(createAdditionalServiceRequest.getName().toUpperCase(Locale.ROOT));
        this.additionalServiceDao.save(additionalService);

        return new SuccessResult(BusinessMessages.DATA_ADDED);
    }

    @Override
    public Result update(int id, UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
        checkIfIdExists(id);
        checkIfNameExists(updateAdditionalServiceRequest.getName());

        AdditionalService additionalService = this.additionalServiceDao.getById(id);

        populateAdditionalServiceFields(additionalService,updateAdditionalServiceRequest);

        this.additionalServiceDao.save(additionalService);

        return new SuccessResult(BusinessMessages.DATA_UPDATED + id);
    }

    @Override
    public Result delete(int id) {
       checkIfIdExists(id);

       this.additionalServiceDao.deleteById(id);

       return new SuccessResult(BusinessMessages.DATA_DELETED + id);
    }

    @Override
    public AdditionalService findById(int id) {
        return this.additionalServiceDao.getById(id);
    }

    private void populateAdditionalServiceFields(AdditionalService additionalService, UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
        additionalService.setName(updateAdditionalServiceRequest.getName().toUpperCase(Locale.ROOT));
        additionalService.setDailyPrice(updateAdditionalServiceRequest.getDailyPrice());
    }

    private void checkIfIdExists(int id) {
        if (!this.additionalServiceDao.existsById(id)) {
            throw new BusinessException(BusinessMessages.ADDITIONAL_SERVICE_DOES_NOT_EXISTS);
        }
    }

    private void checkIfNameExists(String name) {
        if (this.additionalServiceDao.existsByName(name.toUpperCase(Locale.ROOT))) {
            throw new BusinessException(BusinessMessages.ADDITIONAL_SERVICE_ALREADY_EXISTS);
        }
    }
}
