package com.example.accountservice.entities;

import com.example.accountservice.enums.AccountType;
import com.example.accountservice.models.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString @AllArgsConstructor @NoArgsConstructor
@Builder
public class Account {
    @Id
    private String accountId;
    private double balance;
    private LocalDate createAt;
    private String currency;

    @Enumerated(EnumType.STRING)
    private AccountType type;
    private Long customerId;

    @Transient
    private Customer customer;
}
