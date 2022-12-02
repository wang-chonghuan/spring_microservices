package com.wang.mgtmsv.mq;

import com.wang.mgtmsv.mq.event.Score;
import com.wang.mgtmsv.mq.event.ScoreEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueuesListener {
    // when @RabbitListener is on class level, use @RabbitHandler to annotate the method
    @RabbitListener(queues="${amqp.queue.score}")
    void handleScoreEvent(final ScoreEvent event) {
        try {
            // todo how to deal with the received scores doesn't belong to the main use cases, so leave it unimplemented
            log.info("handleScoreEvent score list {}",
                    event.getScoreList().stream().map(Score::toString).collect(Collectors.joining(";")));
        } catch (final Exception e) {
            log.error("error when trying to process handleScoreEvent", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
