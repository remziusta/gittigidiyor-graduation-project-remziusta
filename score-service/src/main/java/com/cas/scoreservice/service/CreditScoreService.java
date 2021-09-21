package com.cas.scoreservice.service;

import com.cas.scoreservice.repository.CreditScoreRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditScoreService implements BaseService {

    public static final Logger LOGGER = LogManager.getLogger(CreditScoreService.class);

    CreditScoreRepository creditScoreRepository;

    @Autowired
    public CreditScoreService(CreditScoreRepository creditScoreRepository) {
        this.creditScoreRepository = creditScoreRepository;
    }

    @Override
    public Integer getScoreData(String nationalId) {
        LOGGER.info("CreditScore Service's getScoreData method is running");
        int score = 0;
        int k = Character.getNumericValue(nationalId.charAt(nationalId.length() - 1));

        if (k == 0) {
            score = 2000;
        }
        if (k == 2) {
            score = 550;
        }
        if (k == 4) {
            score = 1000;
        }
        if (k == 6) {
            score = 400;
        }
        if (k == 8) {
            score = 900;
        }

        return score;
    }
}
