package com.cas.scoreservice.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditScoreErrorResponse {
    private int status;
    private String message;
    private long timestamp;
}
