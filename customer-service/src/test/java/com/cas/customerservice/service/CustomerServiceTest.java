package com.cas.customerservice.service;

import com.cas.customerservice.TestUtils;
import com.cas.customerservice.dto.CustomerDto;
import com.cas.customerservice.dto.CustomerPutDto;
import com.cas.customerservice.entity.Customer;
import com.cas.customerservice.exception.CustomerIsAlreadyExistException;
import com.cas.customerservice.exception.CustomerNotFoundException;
import com.cas.customerservice.mappers.MapStructMapperImp;
import com.cas.customerservice.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest extends TestUtils {
    private CustomerRepository repository;

    private MapStructMapperImp mapper;

    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        repository = mock(CustomerRepository.class);
        mapper = mock(MapStructMapperImp.class);

        customerService = new CustomerService(repository, mapper);
    }

    @Test
    public void testFindAllCustomer_itShouldReturnCustomerDtoList() {
        List<Customer> customers = generateCustomers();
        List<CustomerDto> expected = generateCustomerDtoList(customers);

        when(repository.findAll()).thenReturn(customers);
        when(mapper.mapFromCustomerListToCustomerDtoList(customers)).thenReturn(expected);

        List<CustomerDto> actual = customerService.findAllCustomer();


        assertAll(
                () -> assertEquals(actual, expected)
        );

        verify(repository).findAll();
        verify(mapper).mapFromCustomerListToCustomerDtoList(customers);
    }

    @Test
    public void testFindAllCustomer_whenCustomerNationalIdExist_itShouldReturnCustomerDto() {
        String nationalId = "23223232326";

        Customer customer = generateCustomer(nationalId);
        CustomerDto expected = generateCustomerDto(nationalId);

        when(repository.findAllByNationalId(nationalId)).thenReturn(Optional.of(customer));
        when(mapper.mapFromCustomerToCustomerDto(customer)).thenReturn(expected);

        CustomerDto actual = customerService.findByCustomerNationalId(nationalId);

        assertAll(
                () -> assertEquals(actual, expected)
        );

        verify(repository).findAllByNationalId(nationalId);
        verify(mapper).mapFromCustomerToCustomerDto(customer);
    }

    @Test
    public void testFindAllCustomer_whenCustomerNationalIdDoesNotExist_itShouldThrowCustomerNotFoundException() {
        String nationalId = "23223232326";

        when(repository.findAllByNationalId(nationalId)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () ->
                customerService.findByCustomerNationalId(nationalId)
        );

        verify(repository).findAllByNationalId(nationalId);
        verifyNoInteractions(mapper);
    }


    @Test
    public void testSaveCustomer_whenCustomerDoesNotExist_itShouldReturnCustomerDto() {
        String nationalId = "23223232326";
        Customer customer = generateCustomer(nationalId);
        CustomerDto expected = generateCustomerDto(nationalId);

        when(repository.existsByNationalId(nationalId)).thenReturn(false);
        when(mapper.mapFromCustomerDtoToCustomer(expected)).thenReturn(customer);
        when(repository.save(customer)).thenReturn(customer);
        when(mapper.mapFromCustomerToCustomerDto(customer)).thenReturn(expected);

        CustomerDto actual = customerService.saveCustomer(expected);

        assertAll(
                () -> assertEquals(actual, expected)
        );

        verify(repository).save(customer);
        verify(repository).existsByNationalId(nationalId);
        verify(mapper).mapFromCustomerDtoToCustomer(expected);
        verify(mapper).mapFromCustomerToCustomerDto(customer);
    }

    @Test
    public void testSaveCustomer_whenCustomerIsExist_itShouldThrowCustomerIsAlreadyExistException() {
        String nationalId = "23223232326";

        CustomerDto expected = generateCustomerDto(nationalId);

        when(repository.existsByNationalId(nationalId)).thenReturn(true);

        assertThrows(CustomerIsAlreadyExistException.class, () ->
                customerService.saveCustomer(expected)
        );

        verify(repository).existsByNationalId(nationalId);
        verifyNoInteractions(mapper);
    }

    @Test
    public void testUpdateCustomer_whenCustomerDoesNotExist_itShouldReturnCustomerDto(){
        String nationalId = "23223232326";
        String firstName = "Enes";

        CustomerPutDto putDto = generateCustomerPutDto(firstName);
        Customer customer = generateCustomer(nationalId);
        customer.setFirstName(firstName);
        CustomerDto expected = generateCustomerDto(nationalId);

        when(repository.findAllByNationalId(nationalId)).thenReturn(Optional.of(customer));
        when(repository.save(customer)).thenReturn(customer);
        when(mapper.mapFromCustomerToCustomerDto(customer)).thenReturn(expected);


        CustomerDto actual = customerService.updateCustomer(putDto,nationalId);

        assertAll(
                () -> assertEquals(actual,expected)
        );

        verify(repository).save(customer);
        verify(repository).findAllByNationalId(nationalId);
        verify(mapper).mapFromCustomerToCustomerDto(customer);
    }

    @Test
    public void testUpdateCustomer_whenCustomerIsExist_itShouldThrowCustomerNotFoundException(){
        String nationalId = "23223232326";
        String firstName = "Enes";

        CustomerPutDto putDto = generateCustomerPutDto(firstName);

        when(repository.findAllByNationalId(nationalId)).thenReturn(Optional.empty());


        assertThrows(CustomerNotFoundException.class, () ->
                customerService.updateCustomer(putDto,nationalId)
        );

        verify(repository).findAllByNationalId(nationalId);
        verifyNoInteractions(mapper);
    }

    @Test
    public void testDeleteUpdateCustomer_whenCustomerNationalIdExist_itShouldDeleteCustomer(){
        String nationalId = "23223232326";

        Customer customer = generateCustomer(nationalId);

        when(repository.findAllByNationalId(nationalId)).thenReturn(Optional.of(customer));

        customerService.deleteCustomer(nationalId);

        verify(repository).findAllByNationalId(nationalId);
        verify(repository).delete(customer);
    }

    @Test
    public void testDeleteUpdateCustomer_whenCustomerNationalIdDoesNotExist_itShouldThrowCustomerNotFoundException(){
        String nationalId = "23223232326";

        when(repository.findAllByNationalId(nationalId)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () ->
                customerService.deleteCustomer(nationalId)
                );

        verify(repository).findAllByNationalId(nationalId);
    }
}