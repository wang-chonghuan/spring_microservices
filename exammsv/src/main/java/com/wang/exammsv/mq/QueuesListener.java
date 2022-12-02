package com.wang.exammsv.mq;

import com.wang.exammsv.domain.StudentExamResult;
import com.wang.exammsv.mq.event.StudentExamEvent;
import com.wang.exammsv.repository.StudentExamResultRepository;
import com.wang.exammsv.command.GradeCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueuesListener {
    @Autowired
    private StudentExamResultRepository resultRepository;

    // when @RabbitListener is on class level, use @RabbitHandler to annotate the method
    @RabbitListener(queues="${amqp.queue.studentexam}")
    void handleStudentExamEvent(final StudentExamEvent event) {
        try {
            log.info("handleStudentExamEvent, examId {}, content {}", event.getExamId(), event.getStudentIdList());
            event.getStudentIdList().forEach(studentId -> {
                var result =
                        resultRepository.findByStudentIdAndExamId(studentId, event.getExamId()).stream().findFirst();
                if(result.isEmpty()) {
                    resultRepository.save(new StudentExamResult(studentId, event.getExamId(), GradeCommand.GradeState.pending));
                }
            });
        } catch (final Exception e) {
            log.error("error when trying to process handleStudentExamEvent", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
