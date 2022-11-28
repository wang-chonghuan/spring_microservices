package com.wang.studentmsrv.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ScorePublisher extends AbstractPublisher {
    public ScorePublisher(final AmqpTemplate amqpTemplate,
                          @Value("${amqp.exchange.exam}") final String topicExchange,
                          @Value("${amqp.routingkey.score}") final String routingKey) {
        super(amqpTemplate, topicExchange, routingKey);
    }
}
