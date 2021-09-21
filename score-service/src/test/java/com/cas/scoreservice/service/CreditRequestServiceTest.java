package com.cas.scoreservice.service;

import com.cas.scoreservice.TestUtils;
import com.cas.scoreservice.dto.CreditRequestDto;
import com.cas.scoreservice.dto.CustomerDto;
import com.cas.scoreservice.entity.CreditRequest;
import com.cas.scoreservice.mapper.MapStructMapperImp;
import com.cas.scoreservice.repository.CreditRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CreditRequestServiceTest extends TestUtils {

    CreditRequestRepository repository;

    CreditScoreService scoreService;

    MapStructMapperImp mapperImp;

    CreditRequestService service;

    @BeforeEach
    public void setUp() {
        repository = mock(CreditRequestRepository.class);
        scoreService = mock(CreditScoreService.class);
        mapperImp = mock(MapStructMapperImp.class);

        service = new CreditRequestService(repository, mapperImp, scoreService);
    }


    @Test
    public void testFindAllRequest_itShouldReturnListCreditRequestDto() {
        List<CreditRequest> creditRequestList = generateCreditRequestList();
        List<CreditRequestDto> expected = generateCreditRequestDtoList();

        when(repository.findAll()).thenReturn(creditRequestList);
        when(mapperImp.mapFromCreditRequestListToCreditRequestDto(creditRequestList)).thenReturn(expected);

        List<CreditRequestDto> actual = service.findAllRequest();

        assertAll(
                () -> assertEquals(actual, expected)
        );

        verify(repository).findAll();
        verify(mapperImp).mapFromCreditRequestListToCreditRequestDto(creditRequestList);

    }

    @Test
    public void testSaveRequest_itShouldReturnCreditRequestDto(){
        CreditRequest creditRequest = generateCreditRequest();
        CreditRequestDto expected = generateCreditRequestDto();

        when(mapperImp.mapFromCreditRequestDtoToCreditRequest(expected)).thenReturn(creditRequest);
        when(repository.save(creditRequest)).thenReturn(creditRequest);
        when(mapperImp.mapFromCreditRequestToCreditRequestDto(creditRequest)).thenReturn(expected);

        CreditRequestDto actual = service.saveRequest(expected);

        assertAll(
                () -> assertEquals(actual,expected)
        );

        verify(repository).save(creditRequest);
        verify(mapperImp).mapFromCreditRequestDtoToCreditRequest(expected);
        verify(mapperImp).mapFromCreditRequestToCreditRequestDto(creditRequest);
    }

    @Test
    public void testCalculateRequest_itShouldReturnCreditRequestDto(){
        CustomerDto customerDto = generateCustomerDto();
        Integer score = new Integer(0);
        CreditRequestDto excepted = generateCreditRequestDto();

        when(scoreService.getScoreData(customerDto.getNationalId())).thenReturn(score);
        when(service.saveRequest(excepted)).thenReturn(excepted);

        CreditRequestDto actual = service.calculateRequest(customerDto);

        assertAll(
                () -> assertEquals(actual,excepted)
        );

        verify(scoreService).getScoreData(customerDto.getNationalId());
    }

}