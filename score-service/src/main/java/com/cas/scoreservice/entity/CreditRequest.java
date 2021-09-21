package com.cas.scoreservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("creditRequest")
public class CreditRequest {
    @Id
    private String id;

    private String nationalId;

    private Integer limit;

    private Boolean status;

    @CreatedDate
    @JsonIgnore
    private Date requestDate;
}
