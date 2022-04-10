package com.turkcell.rentACar2.dataAccess.abstracts;

import com.turkcell.rentACar2.entities.concretes.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDao extends JpaRepository<Car, Integer> {
    List<Car> findByDailyPriceLessThanEqual(double dailyPrice);

    List<Car> findAllByModelYear(int modelYear);

    List<Car> findByColorId(int colorId);

    boolean existsById(int id);
}



























