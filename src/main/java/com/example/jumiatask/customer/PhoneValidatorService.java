package com.example.jumiatask.customer;

 ;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.Pageable;
 import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class PhoneValidatorService {


    @Autowired
    private CustomerRepository customerRepository;

    public final  String CAMEROON_VALIDATOR = "\\(237\\) ?[2368]\\d{7,8}$";
    public final  String CAMEROON_CODE = "237";
    public final  String COUNTRY_CAMEROON = "CAMEROON";

    public final  String ETHIOPIA_VALIDATOR = "\\(251\\) ?[1-59]\\d{8}$";
    public final  String ETHIOPIA_CODE = "251";
    public final  String COUNTRY_ETHIOPIA = "ETHIOPIA";

    public final String MOROCCO_VALIDATOR = "\\(212\\) ?[5-9]\\d{8}$";
    public final String MOROCCO_CODE = "212";
    public final  String COUNTRY_MOROCCO = "MOROCCO";

    private final String MOZAMBIQUE_VALIDATOR = "\\(258\\) ?[28]\\d{7,8}$";
    public final String MOZAMBIQUE_CODE = "258";
    public final  String COUNTRY_MOZAMBIQUE = "MOZAMBIQUE";

    private final String UGANDA_VALIDATOR = "\\(256\\) ?\\d{9}$";
    public final String UGANDA_CODE = "256";
    public final  String COUNTRY_UGANDA = "UGANDA";


   public Customer validateCountry(Customer customer) {
        String phoneCode = customer.getPhone().substring(1,4);
        String restPhone = customer.getPhone().substring(6);
        customer.setWholePhoneNumber(phoneCode + restPhone);
        int code = Integer.parseInt(phoneCode);

        switch (code) {
            case 237 -> {

                if ((Pattern.compile(CAMEROON_VALIDATOR)).matcher(customer.getPhone()).matches()) {
                 customer.setState("VALID");
                } else {
                    customer.setState("INVALID");
                }
                customer.setCountryCode(phoneCode);
                customer.setCountry(COUNTRY_CAMEROON);
                customer.setPhone(customer.getPhone());
             }
            case 251 -> {

                if ((Pattern.compile(ETHIOPIA_VALIDATOR)).matcher(customer.getPhone()).matches()) {
                     customer.setState("VALID");
                } else {
                    customer.setState("INVALID");
                }
                customer.setCountryCode(phoneCode);
                customer.setCountry(COUNTRY_ETHIOPIA);
                customer.setPhone(customer.getPhone());
             }
            case 212 -> {

                if ((Pattern.compile(MOROCCO_VALIDATOR)).matcher(customer.getPhone()).matches()) {
                     customer.setState("VALID");
                } else {
                    customer.setState("INVALID");
                }
                customer.setCountryCode(phoneCode);
                customer.setCountry(COUNTRY_MOROCCO);
                customer.setPhone(customer.getPhone());
            }
            case 258 -> {

                if ((Pattern.compile(MOZAMBIQUE_VALIDATOR)).matcher(customer.getPhone()).matches()) {
                     customer.setState("VALID");
                } else {
                    customer.setState("INVALID");
                }
                customer.setCountryCode(phoneCode);
                customer.setCountry(COUNTRY_MOZAMBIQUE);
                customer.setPhone(customer.getPhone());
            }
            case 256 -> {

                if ((Pattern.compile(UGANDA_VALIDATOR)).matcher(customer.getPhone()).matches()) {
                     customer.setState("VALID");
                } else {
                    customer.setState("INVALID");
                }
                customer.setCountryCode(phoneCode);

                customer.setCountry(COUNTRY_UGANDA);
                customer.setPhone(customer.getPhone());
             }
            default -> customer.setState("Country does not have Jumia");
        }


        return customer;
    }


    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

//    Validate function

    public void validate(Customer customer){
        validateCountry(customer);
    }


    public List<Customer> getPhoneNumbersWithCountry(String country){

        String cntry  = country.toUpperCase(Locale.ROOT);
        return getAllCustomers()
                .stream()
                .map(this::validateCountry)
                .filter(customer -> customer
                        .getCountry()
                        .equals(cntry))
                .collect(Collectors.toList());
    }


    public List<Customer> getPhoneNumbersWithState(String state){

        String States  = state.toUpperCase(Locale.ROOT);
        return getAllCustomers()
                .stream()
                .map(this::validateCountry)
                .filter(customer -> customer
                        .getState()
                        .toUpperCase(Locale.ROOT)
                        .equals(States))
                .collect(Collectors.toList());
    }



    public List<Customer> getPhoneNumbersWithState(String country, String state){

        String cntry  = country.toUpperCase(Locale.ROOT);
        return getAllCustomers()
                .stream()
                .map(this::validateCountry)
                .filter(customer -> customer
                        .getCountry()
                        .equals(cntry))
                .filter(customer -> customer
                        .getState()
                        .equals(state.toUpperCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    /** retrieve phoneNumbers with countryCode**/
    public List<Customer> getPhoneNumbersWithCountryCode(String countryCode){

        return getAllCustomers()
                .stream()
                .map(this::validateCountry)
                .filter(customer -> customer
                        .getCountryCode()
                        .equals(countryCode))
                .collect(Collectors.toList());
    }



    /** retrieve phoneNumbers with phoneNumber**/
//    public List<Customer> getPhoneNumbersWithPhoneNumber(String phoneNumber){
//
//        return getAllCustomers()
//                .stream()
//                .map(this::validateCountry)
//                .filter(customer -> customer
//                        .getWholePhoneNumber()
//                        .equals(phoneNumber))
//                .collect(Collectors.toList());
//    }


}
