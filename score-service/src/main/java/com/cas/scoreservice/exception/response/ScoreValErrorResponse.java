package com.cas.scoreservice.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreValErrorResponse {

    private int status;
    private String fieldName;
    private String rules;
}
