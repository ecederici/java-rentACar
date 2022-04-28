package com.turkcell.rentACar2.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "invoice_number")
    private int invoiceNumber;

    @Column(name = "invoice_date")
    private LocalDate invoiceDate;

    @Column(name = "total_payment")
    private double totalPayment;

    @Column(name = "total_rent_days")
    private int totalRentalDays;

    @ManyToOne
    @JoinColumn(name = "rental_car_id")
    private RentalCar rentalCar;

    @OneToMany(mappedBy = "invoice")
    private List<Payment> payments;
}
