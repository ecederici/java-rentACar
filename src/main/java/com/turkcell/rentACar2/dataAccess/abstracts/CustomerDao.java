package com.turkcell.rentACar2.dataAccess.abstracts;

import com.turkcell.rentACar2.entities.concretes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository <Customer, Integer> {
    boolean existsByEmail(String password);
}
