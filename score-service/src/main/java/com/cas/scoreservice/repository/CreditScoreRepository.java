package com.cas.scoreservice.repository;

import com.cas.scoreservice.entity.CreditScore;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditScoreRepository extends MongoRepository<CreditScore, String> {

    Optional<CreditScore> findByNationalId(String nationalId);

    boolean existsByNationalId(String id);
}
