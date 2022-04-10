package com.turkcell.rentACar2.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rental_cars")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "Lazy"})
public class RentalCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "rent_date")
    private LocalDate rentDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "execution_date")
    private LocalDate executionDate;

    @Column(name = "current_distance")
    private int currentDistance;

    @Column(name = "last_distance")
    private int lastDistance;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "from_city_id")
    private City fromCity;

    @ManyToOne
    @JoinColumn(name = "to_city_id")
    private City toCity;

    @OneToMany(mappedBy = "rentalCar", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderedAdditionalService> orderedAdditionalServices;
}
