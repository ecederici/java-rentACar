package com.turkcell.rentACar2.dataAccess.abstracts;

import com.turkcell.rentACar2.entities.concretes.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;

@Repository
public interface InvoiceDao extends JpaRepository<Invoice, Integer> {

    List<Invoice> findByRentalCar_CustomerId(int id);

    List<Invoice> findByInvoiceDateBetween(LocalDate startDate, LocalDate endDate);
}
