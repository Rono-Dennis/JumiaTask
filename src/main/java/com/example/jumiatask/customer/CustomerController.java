package com.example.jumiatask.customer;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
@AllArgsConstructor
public  class CustomerController {

    @Autowired
    private PhoneValidatorService phoneValidatorService;



    @RequestMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> customers = phoneValidatorService.getAllCustomers();
        customers.forEach(customer -> {
            phoneValidatorService.validate(customer);
        });

        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customers/{country}")
    public ResponseEntity<List<Customer>> getPhoneNumbersWithCountry(@PathVariable(name = "country") String country){

        List<Customer> customersPhoneNumbersWithCountry = phoneValidatorService.getPhoneNumbersWithCountry(country);
        return ResponseEntity.ok(customersPhoneNumbersWithCountry);
    }



    @PostMapping("/customers/{countryCode}")
    public ResponseEntity<List<Customer>> getPhoneNumbersWithCountryCode(@PathVariable(name = "countryCode") String countryCode)
    {
        List<Customer> customersPhoneNumbersWithCountryCode = phoneValidatorService.getPhoneNumbersWithCountryCode(countryCode);

        return ResponseEntity.ok(customersPhoneNumbersWithCountryCode);
    }

    @RequestMapping(value = "/customers/{country}/{state}")
    public ResponseEntity<List<Customer>> getPhoneNumbersWithState(@PathVariable String state,@PathVariable String country){

        List<Customer> customersPhoneNumbersWithState = phoneValidatorService.getPhoneNumbersWithState(country,state);
        return ResponseEntity.ok(customersPhoneNumbersWithState);
    }

}
