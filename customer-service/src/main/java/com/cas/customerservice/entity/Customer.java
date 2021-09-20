package com.cas.customerservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("customer")
public class Customer {
    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private Integer monthlyIncome;

    @Indexed(unique = true)
    private String nationalId;

    @CreatedDate
    @JsonIgnore
    private Date requestDate;


}
