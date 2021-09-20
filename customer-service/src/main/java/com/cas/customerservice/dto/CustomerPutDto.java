package com.cas.customerservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPutDto {

    @ApiModelProperty(example = "Your first name. (Your first names length must be 3 and 30 character and only letter)")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Your first name must only be letters.")
    @Size(min = 3, max = 30, message = "First name must be length 3 and 30 character.")
    @NotBlank(message = "Firstname is mandatory.")
    private String firstName;

    @ApiModelProperty(example = "Your last name. (Your last names length must be 2 and 30 character and only letter)")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Your last name must only be letters.")
    @Size(min = 2, max = 30, message = "Last name must be length 2 and 30 character.")
    @NotBlank(message = "Lastname is mandatory.")
    private String lastName;

    @ApiModelProperty(example = "11 character and number (05554358754)")
    @Pattern(regexp = "^(05)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$", message = "Phone number must be 10 characters and numbers only.")
    @NotBlank(message = "Phone number is mandatory.")
    private String phoneNumber;

    @ApiModelProperty(example = "Your monthly income.")
    @NotNull(message = "Monthly income is mandatory")
    private Integer monthlyIncome;
}
