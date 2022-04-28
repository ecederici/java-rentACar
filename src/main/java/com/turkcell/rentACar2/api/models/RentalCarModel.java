package com.turkcell.rentACar2.api.models;

import com.turkcell.rentACar2.business.requests.OrderedAdditionalServiceRequest.CreateOrderedAdditionalServiceRequest;
import com.turkcell.rentACar2.business.requests.RentalCarRequest.CreateRentalCarRequest;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class RentalCarModel {
    @Valid
    CreateRentalCarRequest createRentalCarRequest;

    @Valid
    List<CreateOrderedAdditionalServiceRequest> createOrderedAdditionalServiceList;
}
