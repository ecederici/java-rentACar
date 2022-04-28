package com.turkcell.rentACar2.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "rental_car_id")
    private RentalCar rentalCar;

    @Column(name = "payment_amount")
    private double paymentAmount;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}
