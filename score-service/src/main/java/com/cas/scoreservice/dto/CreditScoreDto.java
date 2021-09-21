package com.cas.scoreservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditScoreDto {

    @ApiModelProperty(example = "11 character and number")
    @Pattern(regexp = "^[1-9]{1}[0-9]{9}[02468]{1}$", message = "National Number must be 11 characters and numbers only")
    @NotBlank(message = "National id is mandatory.")
    private String nationalId;

    @ApiModelProperty(example = "0 - 1000")
    @Min(value=0, message="Must be equal or greater than 0")
    @Max(value=1000, message="Must be equal or less than 1000")
    @NotNull(message = "Score is mandatory.")
    private Integer score;
}
