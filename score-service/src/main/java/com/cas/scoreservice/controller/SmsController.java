package com.cas.scoreservice.controller;

import com.cas.scoreservice.dto.CreditRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sms")
public class SmsController {

    @PostMapping("")
    public ResponseEntity<String> sendSms(@RequestBody CreditRequestDto creditRequestDto){
        String status = creditRequestDto.getStatus() ? "onaylandı":"onaylanmadı";
        if(creditRequestDto.getStatus() == false)
            return new ResponseEntity<>("Krediniz " + status + ". Lütfen daha sonra tekrar deneyiniz.", HttpStatus.OK);
        return new ResponseEntity<>("Krediniz " + status + ". Belirlenen limitiniz : " + creditRequestDto.getLimit(), HttpStatus.OK );
    }
}
