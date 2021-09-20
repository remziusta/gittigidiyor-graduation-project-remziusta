package com.cas.customerservice.repository;

import com.cas.customerservice.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer,String> {

    boolean existsByNationalId(String nationalId);

    Optional<Customer> findAllByNationalId(String nationalId);
}
