package com.example.jumiatask.customer;

public record CustomerRegistrationRequest(

    String name,
    String phone,
    String country,
    String state,
    String countryCode,
    String wholePhoneNumber){
}
