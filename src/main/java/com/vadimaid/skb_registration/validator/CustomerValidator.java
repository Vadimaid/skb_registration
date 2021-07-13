package com.vadimaid.skb_registration.validator;

import com.vadimaid.skb_registration.dto.CustomerDto;
import com.vadimaid.skb_registration.exception.ApiException;
import com.vadimaid.skb_registration.exception.InvalidDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CustomerValidator {

    public void validateCustomer(CustomerDto customer) throws ApiException {
        if (Objects.isNull(customer)) {
            throw new InvalidDataException("Incoming data cannot be null");
        }
        if (customer.getLogin().isEmpty()) {
            throw new InvalidDataException("Login cannot be null or empty");
        }
        if (customer.getPassword().isEmpty()) {
            throw new InvalidDataException("Password cannot be null or empty");
        }
        if (customer.getEmail().isEmpty()) {
            throw new InvalidDataException("Email cannot be null or empty");
        }
        if (customer.getFullName().isEmpty()) {
            throw new InvalidDataException("Name of customer cannot be null or empty");
        }
    }

}
