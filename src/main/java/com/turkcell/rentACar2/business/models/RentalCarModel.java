package com.turkcell.rentACar2.business.models;

import com.turkcell.rentACar2.business.requests.OrderedAdditionalServiceRequest.CreateOrderedAdditionalServiceRequest;
import com.turkcell.rentACar2.business.requests.RentalCarRequest.CreateRentalCarRequest;
import lombok.Data;

import java.util.List;

@Data
public class RentalCarModel {

    CreateRentalCarRequest createRentalCarRequest;

    List<CreateOrderedAdditionalServiceRequest> createOrderedAdditionalServiceList;
}
