package com.wang.exammsv.service.command;

import java.util.List;

public class CalculateScoreCommand implements GradeCommand {
    @Override
    public void execute(long examId, List<ResultGradeDecorator> resultDecoratorList) throws Exception {
        resultDecoratorList.forEach(ResultGradeDecorator::calculateScore);
    }
}
