package com.cas.scoreservice.service;

import com.cas.scoreservice.dto.CreditRequestDto;
import com.cas.scoreservice.dto.CreditScoreDto;
import com.cas.scoreservice.dto.CustomerDto;

import java.util.List;

public interface BaseService {


    //--------------------------- CREDIT REQUEST SERVICE METHODS ---------------------------//

    default List<CreditRequestDto> findAllRequest(){return null;};

    default CreditRequestDto saveRequest(CreditRequestDto creditRequestDto){return null;}

    default CreditRequestDto calculateRequest(CustomerDto customerDto){return null;}

    default Integer getScoreData(String nationalId){return 0;}

    //--------------------------------------------------------------------------------------//

    //--------------------------- CREDIT SCORE METHODS ---------------------------//

    default List<CreditScoreDto> findAllScore(){return null;}

    default CreditScoreDto findScoreByNationalId(String nationalId){return null;}

    default CreditScoreDto saveScore(CreditScoreDto creditScoreDto){return null;}

    default CreditScoreDto updateScore(CreditScoreDto scoreDto, String nationalId){return null;}

    default void deleteScore(String nationalId){}

    //--------------------------------------------------------------------------------------//


}
