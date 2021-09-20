package com.cas.customerservice.service;

import com.cas.customerservice.dto.CustomerDto;
import com.cas.customerservice.dto.CustomerPutDto;
import com.cas.customerservice.entity.Customer;
import com.cas.customerservice.exception.CustomerIsAlreadyExistException;
import com.cas.customerservice.exception.CustomerNotFoundException;
import com.cas.customerservice.mappers.MapStructMapperImp;
import com.cas.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author remzi
 * @version 1.0.0
 * @since 2021-09-16
 */
@Service
@RequiredArgsConstructor
public class CustomerService implements BaseService{

    private final CustomerRepository customerRepository;

    private final MapStructMapperImp mapStructMapperImp;

    public static final Logger LOGGER = LogManager.getLogger(CustomerService.class);

    /**
     *
     * @return Returns all customers as a list
     */
    @Transactional(readOnly = true)
    @Override
    public List<CustomerDto> findAllCustomer(){
        LOGGER.info("Customer service's findAll method is running.");
        return mapStructMapperImp.mapFromCustomerListToCustomerDtoList(customerRepository.findAll());
    }

    /**
     *
     * @param nationalId number is unique
     * @return the customer who owns the given unique ID number
     */
    @Transactional(readOnly = true)
    @Override
    public CustomerDto findByCustomerNationalId(String nationalId){
        LOGGER.info("Customer service's findByCustomerNationalId method is running.");
        return mapStructMapperImp.mapFromCustomerToCustomerDto(findByIdCustomer(nationalId));
    }

    /**
     * Registers a new customer.
     * @param dto
     * @return The registered customer is returned to show that the registration was successful
     */
    @Transactional
    @Override
    public CustomerDto saveCustomer(CustomerDto dto){
        LOGGER.info("Customer service's save method is running.");

        boolean isExist = customerRepository.existsByNationalId(dto.getNationalId());

        if (isExist){
            LOGGER.error("Customer with SSID : " + dto.getNationalId() + " is already exists!");
            throw new CustomerIsAlreadyExistException("Customer with National ID : " + dto.getNationalId() + " is already exists!");
        }

        Customer customer = mapStructMapperImp.mapFromCustomerDtoToCustomer(dto);
        CustomerDto customerDto = mapStructMapperImp.mapFromCustomerToCustomerDto(customerRepository.save(customer));

        LOGGER.info(customerDto.getFirstName() + " " + customerDto.getLastName() + " is saved.");
        return customerDto;
    }

    /**
     * Receives the data to be updated with the unique customer ID number and updates the database.
     * @param putDto
     * @param nationalId number is unique
     * @return the new information of the updated customer
     */
    @Transactional
    @Override
    public CustomerDto updateCustomer(CustomerPutDto putDto, String nationalId) {
        LOGGER.info("Customer service's update method is running.");
        Customer customer = findByIdCustomer(nationalId);

        customer.setFirstName(putDto.getFirstName());
        customer.setLastName(putDto.getLastName());
        customer.setMonthlyIncome(putDto.getMonthlyIncome());
        customer.setPhoneNumber(putDto.getPhoneNumber());
        LOGGER.info("Customer is setting in customer score service by update method");

        return mapStructMapperImp.mapFromCustomerToCustomerDto(customerRepository.save(customer));
    }

    /**
     * If there is a customer with the given ID number, it will be deleted.
     * @param nationalId number is unique
     */
    @Transactional
    @Override
    public void deleteCustomer(String nationalId){
        LOGGER.info("Customer service's deleteCustomer method is running.");
        Customer customer = findByIdCustomer(nationalId);
        customerRepository.delete(customer);
        LOGGER.info("The person with the "+ customer.getNationalId() +" has been deleted.");
    }

    /**
     *
     * @param nationalId number is unique
     * @return the customer who owns the given unique ID number
     */
    private Customer findByIdCustomer(String nationalId){
        Customer customer = customerRepository.findAllByNationalId(nationalId).orElseThrow(() -> new CustomerNotFoundException("No customer found for this id : " + nationalId));
        LOGGER.info("Customer is find.");
        return customer;
    }


}
