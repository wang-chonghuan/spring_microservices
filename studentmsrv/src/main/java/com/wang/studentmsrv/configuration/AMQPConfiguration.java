package com.wang.studentmsrv.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

@Configuration
public class AMQPConfiguration {

    // student不需要交换机，但是也创建，这样就不用等teacher启动后它才能启动了
    // 交换机的创建是幂等的，所以创建多次也没关系
    @Bean
    public TopicExchange teacherExchange(@Value("${amqp.exchange.teacher}") final String exchangeName) {
        return ExchangeBuilder.topicExchange(exchangeName).durable(true).build();
    }

    @Bean
    public Queue blankpaperQueue(@Value("${amqp.queue.blankpaper}") final String queueName) {
        return QueueBuilder.durable(queueName).build();
    }

    @Bean
    public Queue studentExamQueue(@Value("${amqp.queue.studentexam}") final String queueName) {
        return QueueBuilder.durable(queueName).build();
    }

    @Bean
    public Binding blankpaperBinding(final Queue blankpaperQueue, final TopicExchange teacherExchange) {
        return BindingBuilder.bind(blankpaperQueue).to(teacherExchange).with("blankpaper.routingkey");
    }

    @Bean
    public Binding studentExamBinding(final Queue studentExamQueue, final TopicExchange teacherExchange) {
        return BindingBuilder.bind(studentExamQueue).to(teacherExchange).with("studentexam.routingkey");
    }

    @Bean
    public RabbitListenerConfigurer rabbitListenerConfigurer(
            final MessageHandlerMethodFactory messageHandlerMethodFactory) {
        return c -> c.setMessageHandlerMethodFactory(messageHandlerMethodFactory);
    }

    @Bean
    public MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory =
                new DefaultMessageHandlerMethodFactory();
        final MappingJackson2MessageConverter jsonConverter =
                new MappingJackson2MessageConverter();
        jsonConverter.getObjectMapper().registerModule(
                new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));
        factory.setMessageConverter(jsonConverter);
        return factory;
    }


}
