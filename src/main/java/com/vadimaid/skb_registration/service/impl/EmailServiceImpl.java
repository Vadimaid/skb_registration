package com.vadimaid.skb_registration.service.impl;

import com.vadimaid.skb_registration.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendEmail(String toAddress, String messageBody) {
        log.info("Message sent to {}, body {}.", toAddress, messageBody);
    }
}
