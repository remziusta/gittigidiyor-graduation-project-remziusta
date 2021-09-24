package com.cas.feignclient.controller;

import com.cas.feignclient.dto.CustomerDto;
import com.cas.feignclient.dto.CustomerPutDto;
import com.cas.feignclient.service.CustomerClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerClient customerClient;

    private final Logger LOGGER = LogManager.getLogger(CustomerController.class);

    @Autowired
    public CustomerController(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    @GetMapping("")
    public ResponseEntity<List<CustomerDto>> getAllCustomer(){
        LOGGER.info("GET ALL CUSTOMER BY FEIGN CLIENT");
        return customerClient.findAllCustomer();
    }

    @GetMapping("/{nationalId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable String nationalId){
        LOGGER.info("GET CUSTOMER BY FEIGN CLIENT");
        return customerClient.findCustomer(nationalId);
    }

    @PostMapping("")
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody @Valid CustomerDto customerDto){
        LOGGER.info("SAVE CUSTOMER BY FEIGN CLIENT");
        return customerClient.saveCustomer(customerDto);
    }

    @DeleteMapping("/{nationalId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String nationalId){
        LOGGER.info("DELETE CUSTOMER BY FEIGN CLIENT");
        return customerClient.deleteCustomer(nationalId);
    }

    @PutMapping("/{nationalId}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable String nationalId, @RequestBody @Valid CustomerPutDto putDto){
        LOGGER.info("UPDATE CUSTOMER BY FEIGN CLIENT");
        return customerClient.update(nationalId,putDto);
    }
}
