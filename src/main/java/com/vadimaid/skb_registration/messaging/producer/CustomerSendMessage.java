package com.vadimaid.skb_registration.messaging.producer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSendMessage {
    private String login;
    private String email;
    private String fullName;
}
