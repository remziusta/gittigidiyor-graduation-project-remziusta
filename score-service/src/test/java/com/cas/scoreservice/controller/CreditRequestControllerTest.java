package com.cas.scoreservice.controller;

import com.cas.scoreservice.TestUtils;
import com.cas.scoreservice.dto.CreditRequestDto;
import com.cas.scoreservice.dto.CustomerDto;
import com.cas.scoreservice.service.CreditRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CreditRequestControllerTest extends TestUtils {

    CreditRequestService creditRequestService;

    CreditRequestController controller;
    @BeforeEach
    public void setUp(){
        creditRequestService = mock(CreditRequestService.class);

        controller = new CreditRequestController(creditRequestService);
    }


    @Test
    public void testRequestCalculate_itShoulReturnCreditRequestDto(){
        CustomerDto customerDto = generateCustomerDto();
        CreditRequestDto expected = generateCreditRequestDto();

        when(creditRequestService.calculateRequest(customerDto)).thenReturn(expected);

        CreditRequestDto actual = controller.requestCalculation(customerDto).getBody();

        assertAll(
                () -> assertEquals(actual,expected)
        );

        verify(creditRequestService).calculateRequest(customerDto);
    }
}