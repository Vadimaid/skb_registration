package com.vadimaid.skb_registration.messaging.consumer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerReceiveMessage {
    private String email;
    private String messageText;
}
