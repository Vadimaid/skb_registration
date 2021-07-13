package com.vadimaid.skb_registration.service;

public interface EmailService {

    void sendEmail(String toAddress, String messageBody);
}
