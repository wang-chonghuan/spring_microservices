package com.wang.exammsv.service.command;

import com.wang.exammsv.service.decorator.ResultGradeDecorator;
import com.wang.exammsv.service.interpreter.BonusCalculator;

import java.util.List;

public class AddBonusCommand implements GradeCommand {
    @Override
    public void execute(long examId, List<ResultGradeDecorator> resultDecoratorList) throws Exception {
        resultDecoratorList.forEach(rd -> {
            var newScore = BonusCalculator.interpret(this.expression, rd.getScore());
            rd.setScore(newScore);
        });
    }

    private String expression;

    public AddBonusCommand(String expression) {
        this.expression = expression;
    }
}
