package com.cas.customerservice.service;

import com.cas.customerservice.dto.CustomerDto;
import com.cas.customerservice.dto.CustomerPutDto;

import java.util.List;

public interface BaseService {

    //-------------------------------CUSTOMER SERVICE METHODS-----------------------------

    default List<CustomerDto> findAllCustomer() {
        return null;
    }

    default CustomerDto findByCustomerNationalId(String nationalId) {
        return null;
    }

    default CustomerDto saveCustomer(CustomerDto dto) {
        return null;
    }

    default CustomerDto updateCustomer(CustomerPutDto putDto, String nationalId) {
        return null;
    }

    default void deleteCustomer(String nationalId) {
    }
    //-----------------------------------------------------------------------------------

}
