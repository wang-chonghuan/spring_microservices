package com.wang.exammsv.service.command;

import com.wang.exammsv.domain.StudentExamResult;

import java.util.List;

public class AddBonusCommand extends GradeCommand {
    @Override
    public void execute(List<StudentExamResult> resultList) throws BreakChainException {

    }

    private String expression;

    public AddBonusCommand(String expression) {
        this.expression = expression;
    }
}
