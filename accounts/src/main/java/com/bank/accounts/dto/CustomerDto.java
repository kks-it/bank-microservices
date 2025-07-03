package com.bank.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    @NotBlank(message = "Customer name cannot be blank")
    private String name;

    @Email
    @NotBlank(message = "Email address cannot be null or blank")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile Number must be 10 digits")
    @NotBlank(message = "Mobile number address cannot be null or blank")
    private String mobileNumber;

    private AccountDto accountDto;
}
