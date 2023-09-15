package com.example.notification.config;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AmqConfig {
    public final static String NOTIFICATION_QUEUE = "NOTIFICATION_QUEUE";
    public final static String MESSAGE_EXCHANGE = "MESSAGE_EXCHANGE";
    public final static String ROUTING_KEY = "ROUTING_KEY";
    @Bean
    public Queue notificationQueue(){
        return new Queue(NOTIFICATION_QUEUE);
    }
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(MESSAGE_EXCHANGE);
    }
    @Bean
    public Binding notificationQueueBinding(Queue queue){
        return BindingBuilder.bind(queue).to(exchange()).with(ROUTING_KEY);
    }
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;

    }

}
