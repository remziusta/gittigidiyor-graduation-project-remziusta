package com.cas.customerservice.controller;

import com.cas.customerservice.dto.CustomerDto;
import com.cas.customerservice.dto.CustomerPutDto;
import com.cas.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController implements BaseController{

    private final CustomerService customerService;

    @Override
    @GetMapping("")
    public ResponseEntity<List<CustomerDto>> findAllCustomer() {
        return new ResponseEntity<>(customerService.findAllCustomer(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{nationalId}")
    public ResponseEntity<CustomerDto> findByIdCustomer(@PathVariable String nationalId) {
        return new ResponseEntity<>(customerService.findByCustomerNationalId(nationalId), HttpStatus.OK);
    }

    @PostMapping("")
    @Override
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody @Valid CustomerDto customerDto) {
        return new ResponseEntity<>(customerService.saveCustomer(customerDto), HttpStatus.OK);
    }

    @PutMapping("/{nationalId}")
    @Override
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable String nationalId, @RequestBody @Valid CustomerPutDto customerPutDto) {
        return new ResponseEntity<>(customerService.updateCustomer(customerPutDto, nationalId), HttpStatus.OK);
    }

    @DeleteMapping("/{nationalId}")
    @Override
    public ResponseEntity<String> deleteCustomer(@PathVariable String nationalId) {
        customerService.deleteCustomer(nationalId);
        return new ResponseEntity<>("Deleted customer with id : " + nationalId, HttpStatus.OK);
    }

}
