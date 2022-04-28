package com.turkcell.rentACar2.dataAccess.abstracts;

import com.turkcell.rentACar2.entities.concretes.UserCardInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCardInformationDao extends JpaRepository<UserCardInformation, Integer> {

    List<UserCardInformation> findByCustomerId(int customerId);
}
