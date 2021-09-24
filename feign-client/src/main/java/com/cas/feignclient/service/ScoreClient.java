package com.cas.feignclient.service;

import com.cas.feignclient.dto.CreditRequestDto;
import com.cas.feignclient.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@FeignClient(value = "score-service")
public interface ScoreClient {

    @PostMapping("/request")
    ResponseEntity<CreditRequestDto> requestCalculation(@RequestBody @Valid CustomerDto customerDto);

    @PostMapping("/sms")
    ResponseEntity<String> sendSms(@RequestBody CreditRequestDto creditRequestDto);

    @PostMapping("/request/{nationalId}")
    ResponseEntity<List<CreditRequestDto>> getAllRequestByNationalId(@PathVariable String nationalId);
}
