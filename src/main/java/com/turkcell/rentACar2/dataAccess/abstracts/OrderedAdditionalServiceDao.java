package com.turkcell.rentACar2.dataAccess.abstracts;

import com.turkcell.rentACar2.entities.concretes.OrderedAdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedAdditionalServiceDao extends JpaRepository<OrderedAdditionalService, Integer> {

    List<OrderedAdditionalService> findAllByRentalCar_Id(int rentalCarId);

    List<OrderedAdditionalService> findAllByAdditionalService_Id(int additionalServiceId);
}
