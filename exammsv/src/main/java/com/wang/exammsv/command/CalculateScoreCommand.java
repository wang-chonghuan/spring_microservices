package com.wang.exammsv.command;

import com.wang.exammsv.dto.AssembledAnswerResultDecorator;

import java.util.List;

public class CalculateScoreCommand implements GradeCommand {
    @Override
    public void execute(long examId, List<AssembledAnswerResultDecorator> resultDecoratorList) throws Exception {
        resultDecoratorList.forEach(AssembledAnswerResultDecorator::calculateScore);
    }
}
