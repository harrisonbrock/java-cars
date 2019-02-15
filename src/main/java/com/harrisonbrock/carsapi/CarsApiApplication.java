package com.harrisonbrock.carsapi;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CarsApiApplication {

    public final static String EXCHANGE = "CARS";
    public final static String QUEUE_CARS = "carLogs";

    public static void main(String[] args) {
        SpringApplication.run(CarsApiApplication.class, args);
    }

    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue carQueue() {
        return new Queue(QUEUE_CARS);
    }

    @Bean
    public Binding declareBinding() {
        return BindingBuilder.bind(carQueue()).to(appExchange()).with(QUEUE_CARS);
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rt(final ConnectionFactory cf) {
        final RabbitTemplate rt = new RabbitTemplate();
        rt.setMessageConverter(producerJackson2MessageConverter());
        return rt;
    }

}

