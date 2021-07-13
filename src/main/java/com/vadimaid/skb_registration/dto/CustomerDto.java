package com.vadimaid.skb_registration.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
public class CustomerDto {

    @NonNull
    private String login;

    @NonNull
    private String password;

    @NonNull
    private String email;

    @NonNull
    private String fullName;

}
