package com.cas.scoreservice.repository;

import com.cas.scoreservice.dto.CreditRequestDto;
import com.cas.scoreservice.entity.CreditRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRequestRepository extends MongoRepository<CreditRequest, String> {

    List<CreditRequest> findAllByNationalId(String nationalId);
}
