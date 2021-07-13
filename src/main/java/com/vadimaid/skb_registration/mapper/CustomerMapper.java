package com.vadimaid.skb_registration.mapper;

import com.vadimaid.skb_registration.dto.CustomerDto;
import com.vadimaid.skb_registration.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    private final PasswordEncoder passwordEncoder;

    public Customer mapDtoToEntity(CustomerDto source) {
        Customer customer = new Customer();
        customer.setLogin(source.getLogin());
        customer.setPassword(passwordEncoder.encode(source.getPassword()));
        customer.setEmail(source.getEmail());
        customer.setFullname(source.getFullName());
        return customer;
    }

    public CustomerDto mapEntityToDto(Customer source) {
        CustomerDto dto = new CustomerDto();
        dto.setLogin(source.getLogin());
        dto.setEmail(source.getEmail());
        dto.setFullName(source.getFullname());
        return dto;
    }
}
