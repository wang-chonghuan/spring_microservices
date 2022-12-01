package com.wang.exammsv.service.command;

import com.wang.exammsv.service.decorator.ResultGradeDecorator;

import java.util.List;

public interface GradeCommand {
    public void execute(long examId, List<ResultGradeDecorator> resultDecoratorList);

    // when scoring, if all the quiz are scored, set fullyscored, if a part of the quiz are scored, set autoscored.
    public enum GradeState {
        pending, submitted, autograded, fullygraded, broadcasted
    }
}
