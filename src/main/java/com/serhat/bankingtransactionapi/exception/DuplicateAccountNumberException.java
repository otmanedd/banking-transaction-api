package com.serhat.bankingtransactionapi.exception;

public class DuplicateAccountNumberException extends RuntimeException {

    public DuplicateAccountNumberException(String message) {
        super(message);
    }
}