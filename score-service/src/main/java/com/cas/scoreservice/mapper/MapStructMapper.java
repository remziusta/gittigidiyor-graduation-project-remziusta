package com.cas.scoreservice.mapper;

import com.cas.scoreservice.dto.CreditRequestDto;
import com.cas.scoreservice.dto.CreditScoreDto;
import com.cas.scoreservice.entity.CreditRequest;
import com.cas.scoreservice.entity.CreditScore;

import java.util.List;


public interface MapStructMapper {
    CreditScore mapFromCreditScoreDtoToCreditScore(CreditScoreDto creditScoreDto);
    CreditScoreDto mapFromCreditScoreToCreditScoreDto(CreditScore creditScore);

    CreditRequest mapFromCreditRequestDtoToCreditRequest(CreditRequestDto creditRequestDto);
    CreditRequestDto mapFromCreditRequestToCreditRequestDto(CreditRequest creditRequest);

    List<CreditRequestDto> mapFromCreditRequestListToCreditRequestDto(List<CreditRequest> creditRequests);
}
