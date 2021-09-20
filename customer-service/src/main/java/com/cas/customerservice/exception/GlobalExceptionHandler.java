package com.cas.customerservice.exception;

import com.cas.customerservice.exception.response.CustomerErrorResponse;
import com.cas.customerservice.exception.response.CustomerValErrorResponse;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<CustomerErrorResponse> handler(CustomerNotFoundException exception){
        CustomerErrorResponse errorResponse = prepareErrorResponse(HttpStatus.NOT_FOUND,exception.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerIsAlreadyExistException.class)
    public ResponseEntity<CustomerErrorResponse> handler(CustomerIsAlreadyExistException exception){
        CustomerErrorResponse errorResponse = prepareErrorResponse(HttpStatus.NOT_FOUND,exception.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            BindException.class,
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<List<CustomerValErrorResponse>> handleException(MethodArgumentNotValidException e) {
        List<CustomerValErrorResponse> response = new ArrayList<>();
        for (FieldError err : e.getFieldErrors()){
            response.add(new CustomerValErrorResponse(HttpStatus.BAD_REQUEST.value(),err.getField(),err.getDefaultMessage()));
        }

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleException(HttpMessageNotReadableException exception) {
        return new ResponseEntity("You gave an incorrect value for ....", HttpStatus.BAD_REQUEST);
    }
    private CustomerErrorResponse prepareErrorResponse(HttpStatus httpStatus, String message) {
        CustomerErrorResponse response = new CustomerErrorResponse();
        response.setStatus(httpStatus.value());
        response.setMessage(message);
        response.setTimestamp(System.currentTimeMillis());
        return response;
    }
}
