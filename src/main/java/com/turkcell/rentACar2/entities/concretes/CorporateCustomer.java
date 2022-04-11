package com.turkcell.rentACar2.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "corporate_customers")
@PrimaryKeyJoinColumn(name = "customer_id", referencedColumnName = "id")
public class CorporateCustomer extends Customer {

    @Column(name = "customer_id", insertable = false, updatable = false)
    private int customerId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "tax_number", length = 10)
    private String taxNumber;
}
