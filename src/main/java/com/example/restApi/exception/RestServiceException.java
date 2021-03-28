package com.example.restApi.exception;

public class RestServiceException extends Exception {

    public RestServiceException () {
    }

    public RestServiceException (String message) {
        super (message);
    }

    public RestServiceException (Throwable cause) {
        super (cause);
    }

    public RestServiceException (String message, Throwable cause) {
        super (message, cause);
    }
}
