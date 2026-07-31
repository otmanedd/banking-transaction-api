package com.serhat.bankingtransactionapi.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class CreateAccountRequest {

    @NotBlank(message = "Account number cannot be blank")
    private String accountNumber;

    @NotBlank(message = "Owner name cannot be blank")
    private String ownerName;

    @DecimalMin(value = "0.0", inclusive = false, message = "Initial balance must be greater than zero")
    private BigDecimal balance;

    public CreateAccountRequest() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}