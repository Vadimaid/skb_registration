package com.vadimaid.skb_registration.dto;

import com.vadimaid.skb_registration.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    private Long id;
    private String login;
    private String email;
    private String fullName;

    public static CustomerResponse of(@NonNull Customer customer) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(customer.getId());
        customerResponse.setLogin(customer.getLogin());
        customerResponse.setEmail(customer.getEmail());
        customerResponse.setFullName(customer.getFullname());
        return customerResponse;
    }

}
