package com.turkcell.rentACar2.dataAccess.abstracts;

import com.turkcell.rentACar2.entities.concretes.CarDamage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDamageDao extends JpaRepository<CarDamage, Integer> {
    List<CarDamage> findByCar_Id(int carId);
}
