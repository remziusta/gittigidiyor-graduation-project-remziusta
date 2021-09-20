package com.cas.customerservice.controller;

import com.cas.customerservice.TestUtils;
import com.cas.customerservice.dto.CustomerDto;
import com.cas.customerservice.dto.CustomerPutDto;
import com.cas.customerservice.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerControllerTest extends TestUtils {

    private CustomerService customerService;

    private CustomerController controller;

    @BeforeEach
    public void setup(){
        customerService = mock(CustomerService.class);
        controller = new CustomerController(customerService);
    }

    @Test
    public void testFindAllCustomer_itShouldReturnCustomerDtoList(){
        List<CustomerDto> expected = generateCustomerDtos();
        when(customerService.findAllCustomer()).thenReturn(expected);

        List<CustomerDto> actual = controller.findAllCustomer().getBody();

        assertAll(
                () -> assertEquals(actual,expected)
        );

        verify(customerService).findAllCustomer();
    }

    @Test
    public void testFindByIdCustomer_itShouldReturnCustomerDto(){
        String nationalId = "23234532146";
        CustomerDto expected = generateCustomerDto(nationalId);

        when(customerService.findByCustomerNationalId(nationalId)).thenReturn(expected);

        CustomerDto actual = controller.findByIdCustomer(nationalId).getBody();

        assertAll(
                () -> assertEquals(actual,expected)
        );

        verify(customerService).findByCustomerNationalId(nationalId);
    }

    @Test
    public void testSaveCustomer_itShouldReturnCustomerDto(){
        String nationalId = "23234532146";
        CustomerDto expected = generateCustomerDto(nationalId);

        when(customerService.saveCustomer(expected)).thenReturn(expected);

        CustomerDto actual = controller.saveCustomer(expected).getBody();

        assertAll(
                () -> assertEquals(actual,expected)
        );

        verify(customerService).saveCustomer(expected);
    }

    @Test
    public void testUpdateCustomer_itShouldReturnCustomerDto(){
        String nationalId = "23234532146";
        String name = "Cemal";
        CustomerPutDto dto = generateCustomerPutDto(name);
        CustomerDto expected = generateCustomerDto(nationalId);
        when(customerService.updateCustomer(dto,nationalId)).thenReturn(expected);

        CustomerDto actual = controller.updateCustomer(nationalId,dto).getBody();

        assertAll(
                () -> assertEquals(actual,expected)
        );

        verify(customerService).updateCustomer(dto,nationalId);
    }

    @Test
    public void testDeleteCustomer_itShouldReturnString(){
        String nationalId = "23234532146";
        String message = "Deleted customer with id : " + nationalId;

        String actual = controller.deleteCustomer(nationalId).getBody();

        assertAll(
                () -> assertEquals(actual,message)
        );

        verify(customerService).deleteCustomer(nationalId);
    }
}