package com.cas.scoreservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IdLastDigitCheckException extends RuntimeException{
    public IdLastDigitCheckException(String message) {
        super(message);
    }
}
