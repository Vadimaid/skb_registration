package com.vadimaid.skb_registration.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfiguration {

    @Value("${messaging.rabbit.host}")
    private String messagingHost;

    @Value("${messaging.rabbit.producer.queue}")
    private String messagingOutQueue;

    @Value("${messaging.rabbit.consumer.queue}")
    private String messagingInQueue;

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(messagingHost);
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

}
