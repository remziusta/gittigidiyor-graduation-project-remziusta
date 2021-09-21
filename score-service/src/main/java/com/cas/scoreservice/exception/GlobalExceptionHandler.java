package com.cas.scoreservice.exception;

import com.cas.scoreservice.exception.response.CreditScoreErrorResponse;
import com.cas.scoreservice.exception.response.ScoreValErrorResponse;
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

    @ExceptionHandler(CreditScoreNotFoundException.class)
    public ResponseEntity<CreditScoreErrorResponse> handler(CreditScoreNotFoundException exception){
        CreditScoreErrorResponse errorResponse = prepareErrorResponse(HttpStatus.NOT_FOUND,exception.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CreditScoreIsAlreadyExistException.class)
    public ResponseEntity<CreditScoreErrorResponse> handler(CreditScoreIsAlreadyExistException exception){
        CreditScoreErrorResponse errorResponse = prepareErrorResponse(HttpStatus.BAD_REQUEST,exception.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdLastDigitCheckException.class)
    public ResponseEntity<CreditScoreErrorResponse> handler(IdLastDigitCheckException exception){
        CreditScoreErrorResponse errorResponse = prepareErrorResponse(HttpStatus.BAD_REQUEST,exception.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            BindException.class,
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<List<ScoreValErrorResponse>> handleException(MethodArgumentNotValidException e) {
        List<ScoreValErrorResponse> response = new ArrayList<>();
        for (FieldError err : e.getFieldErrors()){
            response.add(new ScoreValErrorResponse(HttpStatus.BAD_REQUEST.value(),err.getField(),err.getDefaultMessage()));
        }

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleException(HttpMessageNotReadableException exception) {
        return new ResponseEntity("You gave an incorrect value for ....", HttpStatus.BAD_REQUEST);
    }
    private CreditScoreErrorResponse prepareErrorResponse(HttpStatus httpStatus, String message) {
        CreditScoreErrorResponse response = new CreditScoreErrorResponse();
        response.setStatus(httpStatus.value());
        response.setMessage(message);
        response.setTimestamp(System.currentTimeMillis());
        return response;
    }
}
