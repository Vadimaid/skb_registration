package com.vadimaid.skb_registration.exception;

import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends ApiException {

    public CustomerNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, "customer_not_found", message);
    }

}
