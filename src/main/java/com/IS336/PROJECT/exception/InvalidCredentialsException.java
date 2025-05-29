package com.IS336.PROJECT.exception;

public class InvalidCredentialsException extends RuntimeException {
    private final String errorCode;

    public InvalidCredentialsException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}

