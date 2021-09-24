package com.cas.scoreservice.controller;

import com.cas.scoreservice.dto.CreditRequestDto;
import com.cas.scoreservice.dto.CustomerDto;
import com.cas.scoreservice.service.CreditRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/request")
public class CreditRequestController {

    CreditRequestService creditRequestService;

    @Autowired
    public CreditRequestController(CreditRequestService creditRequestService) {
        this.creditRequestService = creditRequestService;
    }

    @PostMapping("")
    public ResponseEntity<CreditRequestDto> requestCalculation(@RequestBody @Valid CustomerDto customerDto){
        return new ResponseEntity<>(creditRequestService.calculateRequest(customerDto), HttpStatus.OK);
    }

    @PostMapping("/{nationalId}")
    public ResponseEntity<List<CreditRequestDto>> getRequestByNationalId(@PathVariable String nationalId){
        return  new ResponseEntity<>(creditRequestService.getRequestByNationalId(nationalId),HttpStatus.OK);
    }
}
