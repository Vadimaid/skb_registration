package com.vadimaid.skb_registration.service;

import com.vadimaid.skb_registration.dto.CustomerDto;
import com.vadimaid.skb_registration.entity.Customer;
import com.vadimaid.skb_registration.exception.ApiException;

public interface CustomerService {

    Customer findById(Long id) throws ApiException;

    Customer create(CustomerDto source) throws ApiException;

}
