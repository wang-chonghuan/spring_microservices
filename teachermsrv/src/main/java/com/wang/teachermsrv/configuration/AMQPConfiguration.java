package com.wang.teachermsrv.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfiguration {
    @Bean
    public TopicExchange createTopicExchange(
            @Value("${amqp.exchange.teacher}") final String exchangeName) {

        return ExchangeBuilder.topicExchange(exchangeName).durable(true).build();
    }

    @Bean
    public Jackson2JsonMessageConverter producer2JacksonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
