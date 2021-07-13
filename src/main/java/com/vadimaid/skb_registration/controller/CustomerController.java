package com.vadimaid.skb_registration.controller;

import com.vadimaid.skb_registration.dto.CustomerDto;
import com.vadimaid.skb_registration.dto.CustomerResponse;
import com.vadimaid.skb_registration.exception.ApiException;
import com.vadimaid.skb_registration.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse createCustomer(
            @RequestBody CustomerDto customerDto
    ) throws ApiException {
        return CustomerResponse.of(customerService.create(customerDto));
    }
}
