package com.turkcell.rentACar2.dataAccess.abstracts;

import com.turkcell.rentACar2.entities.concretes.RentalCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalCarDao extends JpaRepository<RentalCar, Integer> {
    List<RentalCar> findAllByCar_Id(int carId);

    boolean existsById(int id);
}
