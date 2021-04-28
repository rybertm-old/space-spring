package com.estudo.space.exception;

import lombok.Getter;

public class ServiceException extends Exception {
    @Getter
    private final Exception ex;
    private final String message;

    public ServiceException(String message, Exception ex) {
        super(message, ex);

        this.ex = ex;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
