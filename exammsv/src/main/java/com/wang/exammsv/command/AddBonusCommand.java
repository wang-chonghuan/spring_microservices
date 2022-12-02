package com.wang.exammsv.command;

import com.wang.exammsv.dto.AssembledAnswerResultDecorator;
import com.wang.exammsv.interpreter.BonusCalculator;

import java.util.List;

public class AddBonusCommand implements GradeCommand {
    @Override
    public void execute(long examId, List<AssembledAnswerResultDecorator> resultDecoratorList) throws Exception {
        resultDecoratorList.forEach(rd -> {
            var newScore = BonusCalculator.interpret(this.expression, rd.getScore());
            rd.setScore(newScore);
        });
    }

    private final String expression;

    public AddBonusCommand(String expression) {
        this.expression = expression;
    }
}
