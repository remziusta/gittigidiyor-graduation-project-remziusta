package com.cas.customerservice.mappers;

import com.cas.customerservice.dto.CustomerDto;
import com.cas.customerservice.entity.Customer;

import java.util.List;

public interface MapStructMapper {
    default Customer mapFromCustomerDtoToCustomer(CustomerDto postDto){return null;}
    default CustomerDto mapFromCustomerToCustomerDto(Customer customer){return null;}
    default List<CustomerDto> mapFromCustomerListToCustomerDtoList(List<Customer> customerList){return null;}
}
