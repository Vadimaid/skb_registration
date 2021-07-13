package com.vadimaid.skb_registration.messaging.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vadimaid.skb_registration.entity.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageSender {

    @Value("${messaging.rabbit.producer.queue}")
    private String messagingQueue;

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(Customer customer) {
        if (Objects.isNull(customer)) {
            log.error("Cannot send empty customer");
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            log.info("Trying to send message to {}", messagingQueue);
            rabbitTemplate.convertAndSend(messagingQueue,
                    mapper.writeValueAsString(convertCustomerToMessage(customer)));
            log.info("Message sent to queue { " + customer + " }");
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }

    private CustomerSendMessage convertCustomerToMessage(Customer customer) {
        CustomerSendMessage exchangeMessage = new CustomerSendMessage();
        exchangeMessage.setLogin(customer.getLogin());
        exchangeMessage.setEmail(customer.getEmail());
        exchangeMessage.setFullName(customer.getFullname());
        return exchangeMessage;
    }
}
