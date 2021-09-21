package com.cas.scoreservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Document("creditScore")
public class CreditScore {

    //@Id
    private String id;

    @Indexed(unique = true)
    private String nationalId;

    private Integer score;

}
