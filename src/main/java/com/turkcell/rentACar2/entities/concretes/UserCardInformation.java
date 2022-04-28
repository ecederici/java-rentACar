package com.turkcell.rentACar2.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_card_informations")
public class UserCardInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "card_no")
    private String cardNo;

    @Column(name = "card_holder")
    private String cardHolder;

    @Column(name = "expire_month")
    private int expireMonth;

    @Column(name = "expire_year")
    private int expireYear;

    @Column(name = "cvv")
    private int cvv;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}

