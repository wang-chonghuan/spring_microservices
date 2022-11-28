package com.wang.teachermsrv.mq;

import com.wang.teachermsrv.mq.event.Score;
import com.wang.teachermsrv.mq.event.ScoreEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
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
            log.info("handleScoreEvent score list {}",
                    event.getScoreList().stream().map(Score::toString).collect(Collectors.joining(";")));
        } catch (final Exception e) {
            log.error("error when trying to process handleScoreEvent", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
