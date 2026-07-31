package com.serhat.bankingtransactionapi;

import com.serhat.bankingtransactionapi.dto.AccountResponse;
import com.serhat.bankingtransactionapi.dto.CreateAccountRequest;
import com.serhat.bankingtransactionapi.dto.DepositRequest;
import com.serhat.bankingtransactionapi.dto.TransferRequest;
import com.serhat.bankingtransactionapi.dto.WithdrawRequest;
import com.serhat.bankingtransactionapi.entity.Account;
import com.serhat.bankingtransactionapi.exception.DuplicateAccountNumberException;
import com.serhat.bankingtransactionapi.exception.InsufficientBalanceException;
import com.serhat.bankingtransactionapi.repository.AccountRepository;
import com.serhat.bankingtransactionapi.service.AccountService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAccount_ShouldCreateAccount_WhenAccountNumberIsUnique() {
        CreateAccountRequest request = new CreateAccountRequest();
        request.setAccountNumber("TR1001");
        request.setOwnerName("Serhat");
        request.setBalance(BigDecimal.valueOf(5000));

        Account savedAccount = new Account();
        savedAccount.setAccountNumber("TR1001");
        savedAccount.setOwnerName("Serhat");
        savedAccount.setBalance(BigDecimal.valueOf(5000));

        when(accountRepository.findByAccountNumber("TR1001")).thenReturn(Optional.empty());
        when(accountRepository.save(any(Account.class))).thenReturn(savedAccount);

        AccountResponse response = accountService.createAccount(request);

        assertEquals("TR1001", response.getAccountNumber());
        assertEquals("Serhat", response.getOwnerName());
        assertEquals(BigDecimal.valueOf(5000), response.getBalance());

        verify(accountRepository).findByAccountNumber("TR1001");
        verify(accountRepository).save(any(Account.class));
    }

    @Test
    void createAccount_ShouldThrowException_WhenAccountNumberAlreadyExists() {
        CreateAccountRequest request = new CreateAccountRequest();
        request.setAccountNumber("TR1001");
        request.setOwnerName("Serhat");
        request.setBalance(BigDecimal.valueOf(5000));

        Account existingAccount = new Account();
        existingAccount.setAccountNumber("TR1001");

        when(accountRepository.findByAccountNumber("TR1001")).thenReturn(Optional.of(existingAccount));

        assertThrows(DuplicateAccountNumberException.class, () -> accountService.createAccount(request));

        verify(accountRepository).findByAccountNumber("TR1001");
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    void deposit_ShouldIncreaseBalance_WhenAccountExists() {
        DepositRequest request = new DepositRequest();
        request.setAccountId(1L);
        request.setAmount(BigDecimal.valueOf(500));

        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(1000));

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));

        AccountResponse response = accountService.deposit(request);

        assertEquals(BigDecimal.valueOf(1500), response.getBalance());

        verify(accountRepository).findById(1L);
        verify(accountRepository).save(account);
    }

    @Test
    void withdraw_ShouldDecreaseBalance_WhenBalanceIsSufficient() {
        WithdrawRequest request = new WithdrawRequest();
        request.setAccountId(1L);
        request.setAmount(BigDecimal.valueOf(300));

        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(1000));

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));

        AccountResponse response = accountService.withdraw(request);

        assertEquals(BigDecimal.valueOf(700), response.getBalance());

        verify(accountRepository).findById(1L);
        verify(accountRepository).save(account);
    }

    @Test
    void withdraw_ShouldThrowException_WhenBalanceIsInsufficient() {
        WithdrawRequest request = new WithdrawRequest();
        request.setAccountId(1L);
        request.setAmount(BigDecimal.valueOf(1500));

        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(1000));

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        assertThrows(InsufficientBalanceException.class, () -> accountService.withdraw(request));

        verify(accountRepository).findById(1L);
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    void transfer_ShouldMoveMoneyBetweenAccounts_WhenBalanceIsSufficient() {
        TransferRequest request = new TransferRequest();
        request.setFromAccountId(1L);
        request.setToAccountId(2L);
        request.setAmount(BigDecimal.valueOf(400));

        Account fromAccount = new Account();
        fromAccount.setBalance(BigDecimal.valueOf(1000));

        Account toAccount = new Account();
        toAccount.setBalance(BigDecimal.valueOf(500));

        when(accountRepository.findById(1L)).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(toAccount));

        String result = accountService.transfer(request);

        assertEquals("Transfer successful", result);
        assertEquals(BigDecimal.valueOf(600), fromAccount.getBalance());
        assertEquals(BigDecimal.valueOf(900), toAccount.getBalance());

        verify(accountRepository).findById(1L);
        verify(accountRepository).findById(2L);
        verify(accountRepository).save(fromAccount);
        verify(accountRepository).save(toAccount);
    }

    @Test
    void getAllAccounts_ShouldReturnPagedAccounts() {
        Account account = new Account();
        account.setAccountNumber("TR1001");
        account.setOwnerName("Serhat");
        account.setBalance(BigDecimal.valueOf(5000));

        when(accountRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(account)));

        var response = accountService.getAllAccounts(0, 10);

        assertEquals(1, response.getTotalElements());
        assertEquals("TR1001", response.getContent().get(0).getAccountNumber());

        verify(accountRepository).findAll(any(Pageable.class));
    }
}