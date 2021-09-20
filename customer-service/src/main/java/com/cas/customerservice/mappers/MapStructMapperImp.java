package com.cas.customerservice.mappers;

import com.cas.customerservice.dto.CustomerDto;
import com.cas.customerservice.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapStructMapperImp implements MapStructMapper{

    @Override
    public Customer mapFromCustomerDtoToCustomer(CustomerDto postDto) {
        if(postDto == null) return null;

        Customer customer = new Customer();
        customer.setFirstName(postDto.getFirstName());
        customer.setLastName(postDto.getLastName());
        customer.setMonthlyIncome(postDto.getMonthlyIncome());
        customer.setNationalId(postDto.getNationalId());
        customer.setPhoneNumber(postDto.getPhoneNumber());

        return customer;
    }

    @Override
    public CustomerDto mapFromCustomerToCustomerDto(Customer customer) {
        if(customer == null) return null;

        CustomerDto dto = new CustomerDto();
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setMonthlyIncome(customer.getMonthlyIncome());
        dto.setNationalId(customer.getNationalId());
        dto.setPhoneNumber(customer.getPhoneNumber());

        return dto;
    }

    @Override
    public List<CustomerDto> mapFromCustomerListToCustomerDtoList(List<Customer> customerList){
        return customerList.stream().map(from -> new CustomerDto(from.getFirstName(),from.getLastName(),from.getPhoneNumber(),from.getMonthlyIncome(),from.getNationalId())).collect(Collectors.toList());
    }
}
