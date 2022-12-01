package com.wang.exammsv.service.command;

import com.wang.exammsv.service.decorator.ResultGradeDecorator;

import java.util.List;

public class CheckGradeStateCommand implements GradeCommand {

    private GradeState legalState;

    @Override
    public void execute(long examId, List<ResultGradeDecorator> resultDecoratorList) {

    }

    public CheckGradeStateCommand(GradeState legalState) {
        this.legalState = legalState;
    }
}
