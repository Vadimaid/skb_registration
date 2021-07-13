package com.vadimaid.skb_registration.exception;

import org.springframework.http.HttpStatus;

public class InvalidDataException extends ApiException {

    public InvalidDataException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "invalid_data", message);
    }
}
