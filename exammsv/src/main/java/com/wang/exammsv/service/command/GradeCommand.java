package com.wang.exammsv.service.command;

import com.wang.exammsv.service.decorator.ResultGradeDecorator;

import java.util.List;

public interface GradeCommand {
    public void execute(long examId, List<ResultGradeDecorator> resultDecoratorList) throws Exception;

    public enum GradeState {
        pending, submitted, autograded, fullygraded, broadcasted
    }
}
