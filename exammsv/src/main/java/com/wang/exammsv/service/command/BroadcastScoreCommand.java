package com.wang.exammsv.service.command;

import com.wang.exammsv.domain.StudentExamResult;
import com.wang.exammsv.mq.ScorePublisher;
import com.wang.exammsv.mq.event.Score;
import com.wang.exammsv.mq.event.ScoreEvent;

import java.util.List;
import java.util.stream.Collectors;

public class BroadcastScoreCommand implements GradeCommand {

    // todo!! 这里用Autowire注入会是空指针，因为这个类是在运行时new出来的，不能自动注入。自动注入的对象必须是服务器启动时装配好的
    private ScorePublisher publisher;

    @Override
    public void execute(long examId, List<StudentExamResult> resultList) throws BreakChainException {
        publisher.publish(new ScoreEvent(resultList.stream()
                .map(r -> new Score(r.getExamId(), r.getStudentId(), r.getScore()))
                .collect(Collectors.toList())));
    }

    public BroadcastScoreCommand(ScorePublisher publisher) {
        this.publisher = publisher;
    }
}
