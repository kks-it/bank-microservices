package com.bank.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account extends BaseEntity {
    @Id
    private Long accountNumber;

    private String accountType;

    private String branchAddress;

    private Long customerId;
}
