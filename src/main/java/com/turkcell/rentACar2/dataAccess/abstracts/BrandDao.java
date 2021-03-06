package com.turkcell.rentACar2.dataAccess.abstracts;

import com.turkcell.rentACar2.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandDao extends JpaRepository<Brand, Integer> {
    boolean existsByName(String name);
}
