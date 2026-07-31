package com.serhat.bankingtransactionapi.controller;

import com.serhat.bankingtransactionapi.dto.AccountResponse;
import com.serhat.bankingtransactionapi.dto.CreateAccountRequest;
import com.serhat.bankingtransactionapi.dto.DepositRequest;
import com.serhat.bankingtransactionapi.dto.TransferRequest;
import com.serhat.bankingtransactionapi.dto.WithdrawRequest;
import com.serhat.bankingtransactionapi.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "Create a new bank account")
    @PostMapping
    public AccountResponse createAccount(@Valid @RequestBody CreateAccountRequest request) {
        return accountService.createAccount(request);
    }

    @Operation(summary = "Get all accounts with pagination")
    @GetMapping
    public Page<AccountResponse> getAllAccounts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return accountService.getAllAccounts(page, size);
    }

    @Operation(summary = "Get account by id")
    @GetMapping("/{id}")
    public AccountResponse getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @Operation(summary = "Deposit money into account")
    @PostMapping("/deposit")
    public AccountResponse deposit(@Valid @RequestBody DepositRequest request) {
        return accountService.deposit(request);
    }

    @Operation(summary = "Withdraw money from account")
    @PostMapping("/withdraw")
    public AccountResponse withdraw(@Valid @RequestBody WithdrawRequest request) {
        return accountService.withdraw(request);
    }

    @Operation(summary = "Transfer money between accounts")
    @PostMapping("/transfer")
    public String transfer(@Valid @RequestBody TransferRequest request) {
        return accountService.transfer(request);
    }
}