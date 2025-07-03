package com.bank.accounts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AccountDto {
    @Pattern(regexp = "^[0-9]{10}$", message = "Account number should be a 10 digit integer")
    @NotBlank(message = "Account Number cannot be null or blank")
    private Long accountNumber;

    @NotBlank(message = "Account type cannot be null or blank")
    private String accountType;

    @NotBlank(message = "Branch address cannot be null or blank")
    private String branchAddress;
}
