package com.cas.scoreservice.service;

import com.cas.scoreservice.dto.CreditRequestDto;
import com.cas.scoreservice.dto.CustomerDto;
import com.cas.scoreservice.entity.CreditRequest;
import com.cas.scoreservice.mapper.MapStructMapperImp;
import com.cas.scoreservice.repository.CreditRequestRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CreditRequestService implements BaseService {
    public static final Logger LOGGER = LogManager.getLogger(CreditRequestService.class);

    CreditRequestRepository creditRequestRepository;

    CreditScoreService creditScoreService;

    MapStructMapperImp mapStructMapperImp;

    public final int CREDIT_LIMIT_MULTIPLIER = 4;

    @Autowired
    public CreditRequestService(CreditRequestRepository creditRequestRepository, MapStructMapperImp mapStructMapperImp, CreditScoreService creditScoreService) {
        this.creditRequestRepository = creditRequestRepository;
        this.mapStructMapperImp = mapStructMapperImp;
        this.creditScoreService = creditScoreService;
    }

    /**
     *
     * @return Returns all request data
     */
    @Transactional(readOnly = true)
    @Override
    public List<CreditRequestDto> findAllRequest(){
        LOGGER.info("Credit request service's findAllScore method is running.");
        return mapStructMapperImp.mapFromCreditRequestListToCreditRequestDto(creditRequestRepository.findAll());
    }

    /**
     * This method saves the incoming loan application to the database.
     * @param creditRequestDto Incoming request information
     * @return Returns the saved request
     */
    @Transactional
    @Override
    public CreditRequestDto saveRequest(CreditRequestDto creditRequestDto){
        LOGGER.info("Credit request service's save method is running.");

        CreditRequest creditRequest = mapStructMapperImp.mapFromCreditRequestDtoToCreditRequest(creditRequestDto);
        CreditRequestDto creditDto = mapStructMapperImp.mapFromCreditRequestToCreditRequestDto(creditRequestRepository.save(creditRequest));

        LOGGER.info("Request is saved");

        return creditDto;
    }


    /**
     * This method calculates the status and limit of the request.
     * @param customerDto Incoming request information
     * @return Returns request result information
     */
    @Transactional
    @Override
    public CreditRequestDto calculateRequest(CustomerDto customerDto) {

        LOGGER.info("Credit request service's calculateRequest method is running.");

        CreditRequestDto requestDto = new CreditRequestDto();

        int score = creditScoreService.getScoreData(customerDto.getNationalId());

            if (score < 500){
                requestDto.setLimit(0);
                requestDto.setStatus(false);
                requestDto.setNationalId(customerDto.getNationalId());
            }
            if (score >= 500 && score < 1000 && customerDto.getMonthlyIncome() <= 5000){
                requestDto.setLimit(10000);
                requestDto.setStatus(true);
                requestDto.setNationalId(customerDto.getNationalId());
            }
            if (score >= 500 && score < 1000 && customerDto.getMonthlyIncome() > 5000){
                requestDto.setLimit(20000);
                requestDto.setStatus(true);
                requestDto.setNationalId(customerDto.getNationalId());
            }
            if (score >= 1000) {
                int newLimit = customerDto.getMonthlyIncome() * CREDIT_LIMIT_MULTIPLIER;
                requestDto.setLimit(newLimit);
                requestDto.setStatus(true);
                requestDto.setNationalId(customerDto.getNationalId());
            }

        CreditRequestDto newRequestDto = saveRequest(requestDto);

        LOGGER.info("Credit Request is calculated.");

        return newRequestDto;
    }

    @Transactional(readOnly = true)
    public List<CreditRequestDto> getRequestByNationalId(String nationalId){
        LOGGER.info("Credit request service's getNationalId method is running.");
        List<CreditRequestDto> myList = mapStructMapperImp.mapFromCreditRequestListToCreditRequestDto(creditRequestRepository.findAllByNationalId(nationalId));
        return myList;
    }
}