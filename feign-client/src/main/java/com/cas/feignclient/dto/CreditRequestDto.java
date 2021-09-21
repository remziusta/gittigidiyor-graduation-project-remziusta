package com.cas.feignclient.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditRequestDto {

    @ApiModelProperty(example = "11 character and number")
    @Pattern(regexp = "^[1-9]{1}[0-9]{9}[02468]{1}$", message = "National Number must be 11 characters and numbers only")
    @NotBlank(message = "National id is mandatory.")
    private String nationalId;

    @ApiModelProperty(example = "You are write your limit")
    @NotNull(message = "Limit is mandatory.")
    private Integer limit;

    @ApiModelProperty(example = "True or False")
    @NotNull(message = "Status is mandatory.")
    private Boolean status;
}
