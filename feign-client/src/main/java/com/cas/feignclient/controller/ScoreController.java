package com.cas.feignclient.controller;


import com.cas.feignclient.dto.CreditRequestDto;
import com.cas.feignclient.dto.CustomerDto;
import com.cas.feignclient.service.CustomerClient;
import com.cas.feignclient.service.ScoreClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/score")
public class ScoreController {

    private ScoreClient scoreClient;

    private CustomerClient customerClient;

    private final Logger LOGGER = LogManager.getLogger(ScoreController.class);

    @Autowired
    public ScoreController(ScoreClient scoreClient, CustomerClient customerClient) {
        this.scoreClient = scoreClient;
        this.customerClient = customerClient;
    }

    @PostMapping("/requestCreditNewCustomer")
    public ResponseEntity<CreditRequestDto> requestCreditNewCustomer(@RequestBody @Valid CustomerDto customerDto) {
        CustomerDto newCustomer = customerClient.saveCustomer(customerDto).getBody();

        CreditRequestDto requestDto = scoreClient.requestCalculation(newCustomer).getBody();

        LOGGER.info("REQUEST CALCULATION BY FEIGN CLIENT");

        String message = scoreClient.sendSms(requestDto).getBody();

        LOGGER.info("THIS MESSAGE IS SEND BY FEIGN CLIENT : " + message);

        return new ResponseEntity<>(requestDto, HttpStatus.OK);
    }

    @PostMapping("/requestCreditByNationalId/{nationalId}")
    public ResponseEntity<CreditRequestDto> requestCreditNationalId(@PathVariable String nationalId) {
        CustomerDto customerDto = customerClient.findCustomer(nationalId).getBody();

        CreditRequestDto requestDto = scoreClient.requestCalculation(customerDto).getBody();

        LOGGER.info("REQUEST CALCULATION BY FEIGN CLIENT");

        String message = scoreClient.sendSms(requestDto).getBody();

        LOGGER.info("THIS MESSAGE IS SEND BY FEIGN CLIENT : " + message);

        return new ResponseEntity<>(requestDto, HttpStatus.OK);
    }

    @PostMapping("/sms")
    public ResponseEntity<String> sendSms(@RequestBody CreditRequestDto creditRequestDto) {
        return scoreClient.sendSms(creditRequestDto);
    }

    @PostMapping("/getRequestByNationalId/{nationalId}")
    public ResponseEntity<List<CreditRequestDto>> getRequestByNationalId(@PathVariable String nationalId) {
        CustomerDto dto = customerClient.findCustomer(nationalId).getBody();
        return scoreClient.getAllRequestByNationalId(dto.getNationalId());
    }
}