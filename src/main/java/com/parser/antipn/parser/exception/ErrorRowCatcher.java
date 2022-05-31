package com.parser.antipn.parser.exception;

public class ErrorRowCatcher extends RuntimeException {
    private String message;

    public ErrorRowCatcher(String error) {
        super(error);
        this.message = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

