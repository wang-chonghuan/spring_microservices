package com.wang.exammsv.service.command;

import com.wang.exammsv.mq.ScorePublisher;
import com.wang.exammsv.mq.event.Score;
import com.wang.exammsv.mq.event.ScoreEvent;
import com.wang.exammsv.service.decorator.ResultGradeDecorator;

import java.util.List;
import java.util.stream.Collectors;

public class BroadcastScoreCommand implements GradeCommand {

    // todo!! Here the injection with Autowire will be a null pointer, because the class is new at runtime and cannot be injected automatically. The object to be injected automatically must be assembled at server startup
    private ScorePublisher publisher;

    @Override
    public void execute(long examId, List<ResultGradeDecorator> resultDecoratorList) {
        publisher.publish(new ScoreEvent(resultDecoratorList.stream()
                .map(r -> new Score(r.getExamId(), r.getStudentId(), r.getScore()))
                .collect(Collectors.toList())));
    }

    public BroadcastScoreCommand(ScorePublisher publisher) {
        this.publisher = publisher;
    }
}
