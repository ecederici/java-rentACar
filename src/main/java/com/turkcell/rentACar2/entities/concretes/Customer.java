package com.turkcell.rentACar2.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
@PrimaryKeyJoinColumn(name="id",referencedColumnName = "id")
public class Customer extends User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false , updatable = false)
    private int id;

    @OneToMany(mappedBy = "customer")
    private List<RentalCar> rentalCars;

    @OneToMany(mappedBy = "customer")
    private List<UserCardInformation> userCardInformations;
}
