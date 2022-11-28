package com.wang.teachermsrv.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


public abstract class AbstractPublisher {
    private final AmqpTemplate amqpTemplate;
    private final String topicExchangeName;
    private final String routingKey;

    public AbstractPublisher(final AmqpTemplate amqpTemplate,
                             final String topicExchange,
                             final String routingKey) {
        this.amqpTemplate = amqpTemplate;
        this.topicExchangeName = topicExchange;
        this.routingKey = routingKey;
    }

    public void publish(final Object event) {
        amqpTemplate.convertAndSend(topicExchangeName, routingKey, event);
    }
}
