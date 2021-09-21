package com.cas.feignclient.service;


import com.cas.feignclient.dto.CustomerDto;
import com.cas.feignclient.dto.CustomerPutDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(value = "customer-service")
public interface CustomerClient {

    @GetMapping("/customer")
    ResponseEntity<List<CustomerDto>> findAllCustomer();

    @GetMapping("/customer/{nationalId}")
    ResponseEntity<CustomerDto> findCustomer(@PathVariable String nationalId);

    @PostMapping("/customer")
    ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerDto customerDto);

    @DeleteMapping("/customer/{nationalId}")
    ResponseEntity<String> deleteCustomer(@PathVariable String nationalId);

    @PutMapping("/customer/{nationalId}")
    ResponseEntity<CustomerDto> update(@PathVariable String nationalId, @RequestBody @Valid CustomerPutDto putDto);
}
