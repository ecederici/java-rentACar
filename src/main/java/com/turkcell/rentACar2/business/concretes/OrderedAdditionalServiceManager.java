package com.turkcell.rentACar2.business.concretes;

import com.turkcell.rentACar2.business.abstracts.OrderedAdditionalServiceService;
import com.turkcell.rentACar2.business.abstracts.RentalCarService;
import com.turkcell.rentACar2.business.constants.messages.BusinessMessages;
import com.turkcell.rentACar2.business.dtos.OrderedAdditionalServiceDto.OrderedAdditionalServiceGetDto;
import com.turkcell.rentACar2.business.dtos.OrderedAdditionalServiceDto.OrderedAdditionalServiceListDto;
import com.turkcell.rentACar2.business.requests.OrderedAdditionalServiceRequest.CreateOrderedAdditionalServiceRequest;
import com.turkcell.rentACar2.core.utilities.exception.BusinessException;
import com.turkcell.rentACar2.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import com.turkcell.rentACar2.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar2.core.utilities.results.SuccessResult;
import com.turkcell.rentACar2.dataAccess.abstracts.OrderedAdditionalServiceDao;
import com.turkcell.rentACar2.entities.concretes.OrderedAdditionalService;
import com.turkcell.rentACar2.entities.concretes.RentalCar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j

public class OrderedAdditionalServiceManager implements OrderedAdditionalServiceService {
    private OrderedAdditionalServiceDao orderedAdditionalServiceDao;
    private ModelMapperService modelMapperService;
    private RentalCarService rentalCarService;

    public OrderedAdditionalServiceManager(OrderedAdditionalServiceDao orderedAdditionalServiceDao,
                                           ModelMapperService modelMapperService,
                                           @Lazy RentalCarService rentalCarService) {
        this.orderedAdditionalServiceDao = orderedAdditionalServiceDao;
        this.modelMapperService = modelMapperService;
        this.rentalCarService = rentalCarService;
    }

    @Override
    public DataResult<List<OrderedAdditionalServiceListDto>> getAll() {
        List<OrderedAdditionalService> orderedAdditionalServices = this.orderedAdditionalServiceDao.findAll();

        List<OrderedAdditionalServiceListDto> orderedAdditionalServiceListDto = orderedAdditionalServices.stream().map(orderedAdditionalService -> this.modelMapperService.forDto().map(orderedAdditionalService, OrderedAdditionalServiceListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(orderedAdditionalServiceListDto, BusinessMessages.DATA_LISTED);
    }

    @Override
    public DataResult<OrderedAdditionalServiceGetDto> getById(int id) {
        checkIfIdExists(id);

        OrderedAdditionalService orderedAdditionalService = this.orderedAdditionalServiceDao.getById(id);

        OrderedAdditionalServiceGetDto response = this.modelMapperService.forDto().map(orderedAdditionalService, OrderedAdditionalServiceGetDto.class);

        return new SuccessDataResult<>(response, BusinessMessages.DATA_BROUGHT + id);
    }

    @Override
    public void add(List<CreateOrderedAdditionalServiceRequest> createOrderedAdditionalServiceRequests,int rentalCarId) {
        for (CreateOrderedAdditionalServiceRequest requests: createOrderedAdditionalServiceRequests) {
            OrderedAdditionalService orderedAdditionalService = this.modelMapperService.forRequest().map(requests,OrderedAdditionalService.class);
            RentalCar rentalCar = this.rentalCarService.findById(rentalCarId);
            orderedAdditionalService.setId(0);
            orderedAdditionalService.setRentalCar(rentalCar);
            this.orderedAdditionalServiceDao.save(orderedAdditionalService);
        }
    }

    @Override
    public Result delete(int id) {
        checkIfIdExists(id);

        this.orderedAdditionalServiceDao.deleteById(id);

        return new SuccessResult(BusinessMessages.DATA_DELETED + id);
    }

    @Override
    public DataResult<List<OrderedAdditionalServiceListDto>> getByRentalCarId(int rentalCarId) {
        List<OrderedAdditionalService> orderedAdditionalServices = this.orderedAdditionalServiceDao.findAllByRentalCar_Id(rentalCarId);

        List<OrderedAdditionalServiceListDto> orderedAdditionalServiceListDto = orderedAdditionalServices.stream().map(orderedAdditionalService -> this.modelMapperService.forDto().map(orderedAdditionalService, OrderedAdditionalServiceListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(orderedAdditionalServiceListDto, BusinessMessages.ORDERED_SERVICE_LISTED_BY_RENTAL_CAR_ID + rentalCarId);
    }

    @Override
    public DataResult<List<OrderedAdditionalServiceListDto>> getByAdditionalServiceId(int additionalServiceId) {
        List<OrderedAdditionalService> orderedAdditionalServices = this.orderedAdditionalServiceDao.findAllByAdditionalService_Id(additionalServiceId);

        List<OrderedAdditionalServiceListDto> orderedAdditionalServiceListDtos = orderedAdditionalServices.stream().map(orderedAdditionalService -> this.modelMapperService.forDto().map(orderedAdditionalService, OrderedAdditionalServiceListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(orderedAdditionalServiceListDtos, BusinessMessages.ORDERED_SERVICE_LISTED_BY_ADDITIONAL_SERVICE_ID + additionalServiceId);
    }

    @Override
    public void checkIfIdExists(int id) {
        if (!this.orderedAdditionalServiceDao.existsById(id)) {
            throw new BusinessException(BusinessMessages.ORDERED_ADDITIONAL_SERVICE_DOES_NOT_EXISTS);
        }
    }
}
