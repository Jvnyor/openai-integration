package com.Jvnyor.openai.service.exception;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException {
    private String typeError;
    private int statusCode;

    public UserException(String message, String typeError, int statusCode) {
        super(message);
        this.typeError = typeError;
        this.statusCode = statusCode;
    }
}
