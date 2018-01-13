package com.hc.sap.anywhere.api.common.exception;

public class AnwRuntimeException extends RuntimeException {

    private int code;
    private String message;

    public AnwRuntimeException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}
