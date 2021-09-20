package com.cas.customerservice;

import com.cas.customerservice.dto.CustomerDto;
import com.cas.customerservice.dto.CustomerPutDto;
import com.cas.customerservice.entity.Customer;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestUtils {

    private final static String id = "1";
    private final static String firstName = "Remzi";
    private final static String lastName = "USTA";
    private final static String phoneNumber = "05555555555";
    private final static String nationalId = "23223232326";
    private final static Integer monthlyIncome = 6999;


    public List<Customer> generateCustomers() {
        List<String> id = Arrays.asList(
                "23223232320",
                "23223232322",
                "23223232324",
                "23223232326",
                "23223232320"
        );

        return IntStream.range(1, 6).mapToObj(i ->
                new Customer(String.valueOf(i),"Remzi", "Usta", "05555555555", i * 1200, id.get(i-1), Date.valueOf(LocalDate.now()))
        ).collect(Collectors.toList());
    }

    public List<CustomerDto> generateCustomerDtoList(List<Customer> customerList){
        return customerList.stream().map(from -> new CustomerDto(from.getFirstName(),from.getLastName(),from.getPhoneNumber(),from.getMonthlyIncome(),from.getNationalId())).collect(Collectors.toList());
    }

    public Customer generateCustomer(String nationalId){
        return new Customer(id,firstName, lastName, phoneNumber, monthlyIncome, nationalId, Date.valueOf(LocalDate.now()));
    }

    public CustomerDto generateCustomerDto(String nationalId){
        return new CustomerDto(firstName,lastName,phoneNumber,monthlyIncome,nationalId);
    }

    public CustomerPutDto generateCustomerPutDto(String firstName){
        return new CustomerPutDto(firstName,lastName,phoneNumber,monthlyIncome);
    }

    public List<CustomerDto> generateCustomerDtos() {
        List<String> id = Arrays.asList(
                "23223232320",
                "23223232322",
                "23223232324",
                "23223232326",
                "23223232320"
        );

        return IntStream.range(1, 6).mapToObj(i ->
                new CustomerDto(firstName, lastName, phoneNumber, i * 1200, id.get(i-1))
        ).collect(Collectors.toList());
    }
}
