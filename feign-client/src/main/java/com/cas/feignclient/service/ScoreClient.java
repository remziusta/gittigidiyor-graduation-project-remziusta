package com.cas.feignclient.service;

import com.cas.feignclient.dto.CreditRequestDto;
import com.cas.feignclient.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(value = "score-service")
public interface ScoreClient {

    @PostMapping("/request")
    ResponseEntity<CreditRequestDto> requestCalculation(@RequestBody @Valid CustomerDto customerDto);

    @PostMapping("/sms")
    ResponseEntity<String> sendSms(@RequestBody CreditRequestDto creditRequestDto);

}
