package com.turkcell.rentACar2.business.abstracts;

import com.turkcell.rentACar2.business.dtos.OrderedAdditionalServiceDto.OrderedAdditionalServiceGetDto;
import com.turkcell.rentACar2.business.dtos.OrderedAdditionalServiceDto.OrderedAdditionalServiceListDto;
import com.turkcell.rentACar2.business.requests.OrderedAdditionalServiceRequest.CreateOrderedAdditionalServiceRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;

import java.util.List;

public interface OrderedAdditionalServiceService {

    DataResult<List<OrderedAdditionalServiceListDto>> getAll();

    DataResult<OrderedAdditionalServiceGetDto> getById(int id);

    void add(List<CreateOrderedAdditionalServiceRequest> createOrderedAdditionalServiceRequests, int rentalId);

    Result delete(int id);

    DataResult<List<OrderedAdditionalServiceListDto>> getByRentalCarId(int rentalCarId);

    DataResult<List<OrderedAdditionalServiceListDto>> getByAdditionalServiceId(int additionalServiceId);

    void checkIfIdExists(int id);
}
