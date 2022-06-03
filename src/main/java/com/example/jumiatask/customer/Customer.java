package com.example.jumiatask.customer;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    private int id;
    private String name;
    private String phone;

    @Transient
    private String country;

    @Transient
    private String state;

    @Transient
    private String countryCode;

    @Transient
    private String wholePhoneNumber;


}

