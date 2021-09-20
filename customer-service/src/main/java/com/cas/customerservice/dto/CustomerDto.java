package com.cas.customerservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    @ApiModelProperty(example = "Your first name. (Your first names length must be 3 and 30 character and only letter)")
    @Pattern(regexp = "^[\\p{L}]+([(\\s)$]?+[\\p{L}]+)+$", message = "Your first name must only be letters.")
    @Size(min = 3, max = 30, message = "First name must be length 3 and 30 character.")
    @NotBlank(message = "Firstname is mandatory.")
    private String firstName;

    @ApiModelProperty(example = "Your last name. (Your last names length must be 2 and 30 character and only letter)")
    @Pattern(regexp = "^[\\p{L}]+([(\\s)$]?+[\\p{L}]+)+$", message = "Your last name must only be letters.")
    @Size(min = 2, max = 30, message = "Last name must be length 2 and 30 character.")
    @NotBlank(message = "Lastname is mandatory.")
    private String lastName;

    @ApiModelProperty(example = "11 character and number (05554358754)")
    @Pattern(regexp = "^(05)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$", message = "Phone number must be 10 characters and numbers only.")
    @NotBlank(message = "Phone number is mandatory.")
    private String phoneNumber;

    @ApiModelProperty(example = "Your monthly income.")
    @Min(value = 0)
    @NotNull(message = "Monthly income is mandatory")
    @Range(min = 1, message = "Monthly Income is greater than 0")
    private Integer monthlyIncome;

    @ApiModelProperty(example = "11 character and number")
    @Pattern(regexp = "^[1-9]{1}[0-9]{9}[02468]{1}$")
    @NotBlank(message = "National id is mandatory.")
    private String nationalId;

}
