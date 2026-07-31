package com.serhat.bankingtransactionapi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountResponse {

    private Long id;
    private String accountNumber;
    private String ownerName;
    private BigDecimal balance;
    private LocalDateTime createdAt;

    public AccountResponse() {
    }

    public AccountResponse(Long id, String accountNumber, String ownerName, BigDecimal balance, LocalDateTime createdAt) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}