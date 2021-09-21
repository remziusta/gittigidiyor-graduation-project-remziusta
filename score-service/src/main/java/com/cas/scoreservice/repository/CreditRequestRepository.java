package com.cas.scoreservice.repository;

import com.cas.scoreservice.entity.CreditRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRequestRepository extends MongoRepository<CreditRequest, String> {
}
