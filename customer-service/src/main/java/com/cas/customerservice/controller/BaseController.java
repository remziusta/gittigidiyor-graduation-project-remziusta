package com.cas.customerservice.controller;

import com.cas.customerservice.dto.CustomerDto;
import com.cas.customerservice.dto.CustomerPutDto;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface BaseController {

    //-------------------------------CUSTOMER CONTROLLER METHOD'S-----------------------------

    default ResponseEntity<List<CustomerDto>> findAllCustomer(){return null;}

    default ResponseEntity<CustomerDto> findByIdCustomer(String nationalId) {
        return null;
    }


    default ResponseEntity<CustomerDto> saveCustomer(CustomerDto customerDto) {
        return null;
    }

    default ResponseEntity<CustomerDto> updateCustomer(String nationalId,CustomerPutDto customerPutDto) {
        return null;
    }

    default ResponseEntity<String> deleteCustomer(String nationalId) {
        return null;
    }

    //----------------------------------------------------------------------------------------

}
