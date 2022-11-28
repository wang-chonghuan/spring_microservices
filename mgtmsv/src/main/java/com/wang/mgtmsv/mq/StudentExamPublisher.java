package com.wang.mgtmsv.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudentExamPublisher extends AbstractPublisher {
    public StudentExamPublisher(final AmqpTemplate amqpTemplate,
                               @Value("${amqp.exchange.mgt}") final String topicExchange,
                               @Value("${amqp.routingkey.studentexam}") final String routingKey) {
        super(amqpTemplate, topicExchange, routingKey);
    }
}
