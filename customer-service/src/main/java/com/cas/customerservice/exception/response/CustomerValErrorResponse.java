package com.cas.customerservice.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerValErrorResponse {

    private int status;
    private String fieldName;
    private String rules;
}
