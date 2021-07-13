package com.vadimaid.skb_registration.service.impl;

import com.vadimaid.skb_registration.dto.CustomerDto;
import com.vadimaid.skb_registration.entity.Customer;
import com.vadimaid.skb_registration.exception.ApiException;
import com.vadimaid.skb_registration.exception.CustomerNotFoundException;
import com.vadimaid.skb_registration.mapper.CustomerMapper;
import com.vadimaid.skb_registration.messaging.producer.MessageSender;
import com.vadimaid.skb_registration.repository.CustomerRepository;
import com.vadimaid.skb_registration.service.CustomerService;
import com.vadimaid.skb_registration.validator.CustomerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerValidator customerValidator;
    private final CustomerMapper customerMapper;
    private final MessageSender messageSender;

    @Override
    public Customer findById(Long id) throws ApiException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException(String.format("Customer with { ID: %d } not found", id));
        }
        return customer.get();
    }

    @Override
    public Customer create(CustomerDto source) throws ApiException {
        customerValidator.validateCustomer(source);
        Customer customer = customerMapper.mapDtoToEntity(source);
        customerRepository.save(customer);
        messageSender.sendMessage(customer);
        return customer;
    }
}
