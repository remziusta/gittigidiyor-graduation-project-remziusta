package com.cas.scoreservice.mapper;

import com.cas.scoreservice.dto.CreditRequestDto;
import com.cas.scoreservice.dto.CreditScoreDto;
import com.cas.scoreservice.entity.CreditRequest;
import com.cas.scoreservice.entity.CreditScore;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper
public class MapStructMapperImp implements MapStructMapper {

    @Override
    public CreditScore mapFromCreditScoreDtoToCreditScore(CreditScoreDto creditScoreDto) {
        if(creditScoreDto == null) return null;

        CreditScore score = new CreditScore();
        score.setNationalId(creditScoreDto.getNationalId());
        score.setScore(creditScoreDto.getScore());

        return score;
    }

    @Override
    public CreditScoreDto mapFromCreditScoreToCreditScoreDto(CreditScore creditScore) {
        if(creditScore == null) return null;

        CreditScoreDto scoreDto = new CreditScoreDto();

        scoreDto.setNationalId(creditScore.getNationalId());
        scoreDto.setScore(creditScore.getScore());

        return scoreDto;
    }

    @Override
    public CreditRequest mapFromCreditRequestDtoToCreditRequest(CreditRequestDto creditRequestDto) {
        if(creditRequestDto == null) return null;

        CreditRequest creditRequest = new CreditRequest();

        creditRequest.setNationalId(creditRequestDto.getNationalId());
        creditRequest.setLimit(creditRequestDto.getLimit());
        creditRequest.setStatus(creditRequestDto.getStatus());

        return creditRequest;
    }

    @Override
    public CreditRequestDto mapFromCreditRequestToCreditRequestDto(CreditRequest creditRequest) {
        if(creditRequest == null) return null;

        CreditRequestDto creditRequestDto = new CreditRequestDto();

        creditRequestDto.setLimit(creditRequest.getLimit());
        creditRequestDto.setStatus(creditRequest.getStatus());
        creditRequestDto.setNationalId(creditRequest.getNationalId());

        return creditRequestDto;
    }

    @Override
    public List<CreditRequestDto> mapFromCreditRequestListToCreditRequestDto(List<CreditRequest> creditRequests) {
        return creditRequests.stream().map(from -> new CreditRequestDto(from.getNationalId(), from.getLimit(), from.getStatus())).collect(Collectors.toList());
    }
}
