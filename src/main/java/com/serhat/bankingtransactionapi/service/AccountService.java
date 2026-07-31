package com.serhat.bankingtransactionapi.service;

import com.serhat.bankingtransactionapi.dto.AccountResponse;
import com.serhat.bankingtransactionapi.dto.CreateAccountRequest;
import com.serhat.bankingtransactionapi.dto.DepositRequest;
import com.serhat.bankingtransactionapi.dto.TransferRequest;
import com.serhat.bankingtransactionapi.dto.WithdrawRequest;
import com.serhat.bankingtransactionapi.entity.Account;
import com.serhat.bankingtransactionapi.exception.AccountNotFoundException;
import com.serhat.bankingtransactionapi.exception.DuplicateAccountNumberException;
import com.serhat.bankingtransactionapi.exception.InsufficientBalanceException;
import com.serhat.bankingtransactionapi.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountResponse createAccount(CreateAccountRequest request) {
        if (accountRepository.findByAccountNumber(request.getAccountNumber()).isPresent()) {
            logger.warn("Account creation failed. Duplicate account number: {}", request.getAccountNumber());

            throw new DuplicateAccountNumberException(
                    "Account number already exists: " + request.getAccountNumber()
            );
        }

        Account account = new Account();
        account.setAccountNumber(request.getAccountNumber());
        account.setOwnerName(request.getOwnerName());
        account.setBalance(request.getBalance());

        Account savedAccount = accountRepository.save(account);

        logger.info("Account created successfully. Account id: {}, account number: {}",
                savedAccount.getId(),
                savedAccount.getAccountNumber()
        );

        return toResponse(savedAccount);
    }

    public Page<AccountResponse> getAllAccounts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        logger.info("Fetching accounts. Page: {}, size: {}", page, size);

        return accountRepository.findAll(pageable)
                .map(this::toResponse);
    }

    public AccountResponse getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Account lookup failed. Account id not found: {}", id);
                    return new AccountNotFoundException("Account not found with id: " + id);
                });

        logger.info("Account fetched successfully. Account id: {}", id);

        return toResponse(account);
    }

    public AccountResponse deposit(DepositRequest request) {
        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> {
                    logger.warn("Deposit failed. Account id not found: {}", request.getAccountId());
                    return new AccountNotFoundException("Account not found with id: " + request.getAccountId());
                });

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            logger.warn("Deposit failed. Invalid amount: {}", request.getAmount());
            throw new RuntimeException("Deposit amount must be greater than zero");
        }

        account.setBalance(account.getBalance().add(request.getAmount()));
        Account savedAccount = accountRepository.save(account);

        logger.info("Deposit successful. Account id: {}, amount: {}, new balance: {}",
                savedAccount.getId(),
                request.getAmount(),
                savedAccount.getBalance()
        );

        return toResponse(savedAccount);
    }

    public AccountResponse withdraw(WithdrawRequest request) {
        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> {
                    logger.warn("Withdraw failed. Account id not found: {}", request.getAccountId());
                    return new AccountNotFoundException("Account not found with id: " + request.getAccountId());
                });

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            logger.warn("Withdraw failed. Invalid amount: {}", request.getAmount());
            throw new RuntimeException("Withdraw amount must be greater than zero");
        }

        if (account.getBalance().compareTo(request.getAmount()) < 0) {
            logger.warn("Withdraw failed. Insufficient balance. Account id: {}, balance: {}, requested amount: {}",
                    account.getId(),
                    account.getBalance(),
                    request.getAmount()
            );

            throw new InsufficientBalanceException("Insufficient balance");
        }

        account.setBalance(account.getBalance().subtract(request.getAmount()));
        Account savedAccount = accountRepository.save(account);

        logger.info("Withdraw successful. Account id: {}, amount: {}, new balance: {}",
                savedAccount.getId(),
                request.getAmount(),
                savedAccount.getBalance()
        );

        return toResponse(savedAccount);
    }

    @Transactional
    public String transfer(TransferRequest request) {
        Account fromAccount = accountRepository.findById(request.getFromAccountId())
                .orElseThrow(() -> {
                    logger.warn("Transfer failed. Sender account id not found: {}", request.getFromAccountId());
                    return new AccountNotFoundException("Sender account not found with id: " + request.getFromAccountId());
                });

        Account toAccount = accountRepository.findById(request.getToAccountId())
                .orElseThrow(() -> {
                    logger.warn("Transfer failed. Receiver account id not found: {}", request.getToAccountId());
                    return new AccountNotFoundException("Receiver account not found with id: " + request.getToAccountId());
                });

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            logger.warn("Transfer failed. Invalid amount: {}", request.getAmount());
            throw new RuntimeException("Transfer amount must be greater than zero");
        }

        if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
            logger.warn("Transfer failed. Insufficient balance. Sender account id: {}, balance: {}, requested amount: {}",
                    fromAccount.getId(),
                    fromAccount.getBalance(),
                    request.getAmount()
            );

            throw new InsufficientBalanceException("Insufficient balance");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(request.getAmount()));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        logger.info("Transfer successful. From account id: {}, to account id: {}, amount: {}",
                fromAccount.getId(),
                toAccount.getId(),
                request.getAmount()
        );

        return "Transfer successful";
    }

    private AccountResponse toResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getAccountNumber(),
                account.getOwnerName(),
                account.getBalance(),
                account.getCreatedAt()
        );
    }
}