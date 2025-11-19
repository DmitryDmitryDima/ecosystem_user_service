package com.ecosystem.usersservice.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {


    @Value("${users.main_events.queue.name}")
    private String USERS_EVENTS_QUEUE_NAME;

    @Value("${users.main_events.exchange.name}")
    private String USERS_EXCHANGE_NAME;

    @Bean
    public Queue usersQueue(){
        return new Queue(USERS_EVENTS_QUEUE_NAME);
    }
    @Bean
    public FanoutExchange usersExchange(){
        return new FanoutExchange(USERS_EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue fanoutQueue,
                           FanoutExchange fanoutExchange) {
        return BindingBuilder
                .bind(fanoutQueue)
                .to(fanoutExchange);

    }





    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();

        factory.setDefaultRequeueRejected(false); // без повторных попыток получить сообщение

        factory.setConnectionFactory(connectionFactory);

        return factory;
    }

}
