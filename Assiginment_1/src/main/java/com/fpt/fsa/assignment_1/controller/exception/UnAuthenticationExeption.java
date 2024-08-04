package com.fpt.fsa.assignment_1.controller.exception;

public class UnAuthenticationExeption extends RuntimeException {
    public UnAuthenticationExeption() {
    }

    public UnAuthenticationExeption(String message) {
        super(message);
    }

    public UnAuthenticationExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public UnAuthenticationExeption(Throwable cause) {
        super(cause);
    }

    public UnAuthenticationExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
