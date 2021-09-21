package com.cas.scoreservice;

import com.cas.scoreservice.dto.CreditRequestDto;
import com.cas.scoreservice.dto.CustomerDto;
import com.cas.scoreservice.entity.CreditRequest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestUtils {

    private final String id = "1";
    private final String nationalId = "32434343454";
    private final Integer limit = 5999;
    private final Boolean status = true;

    public List<CreditRequestDto> generateCreditRequestDtoList() {
        List<String> id = Arrays.asList(
                "23223232320",
                "23223232322",
                "23223232324",
                "23223232326",
                "23223232320"
        );

        return IntStream.range(1, 6).mapToObj(i ->
                new CreditRequestDto(id.get(i - 1), 5999, true)
        ).collect(Collectors.toList());

    }

    public List<CreditRequest> generateCreditRequestList() {
        List<String> id = Arrays.asList(
                "23223232320",
                "23223232322",
                "23223232324",
                "23223232326",
                "23223232320"
        );

        return IntStream.range(1, 6).mapToObj(i ->
                new CreditRequest(String.valueOf(i), id.get(i - 1), 5999, true, Date.valueOf(LocalDate.now()))
        ).collect(Collectors.toList());

    }

    public List<CreditRequestDto> generateCreditDtoList(List<CreditRequest> customerList) {
        return customerList.stream().map(from -> new CreditRequestDto(from.getNationalId(), from.getLimit(), from.getStatus())).collect(Collectors.toList());
    }

    public CreditRequestDto generateCreditRequestDto(){
        return new CreditRequestDto(nationalId,limit,status);
    }

    public CreditRequest generateCreditRequest(){
        return new CreditRequest(id,nationalId,limit,status,Date.valueOf(LocalDate.now()));
    }

    public CustomerDto generateCustomerDto(){
        return new CustomerDto("Remzi","Usta","05555345423",5000,nationalId);
    }
}
