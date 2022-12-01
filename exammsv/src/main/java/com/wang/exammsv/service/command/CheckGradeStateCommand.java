package com.wang.exammsv.service.command;

import com.wang.exammsv.service.decorator.ResultGradeDecorator;

import java.util.List;

public class CheckGradeStateCommand implements GradeCommand {

    private final GradeState legalState;

    @Override
    public void execute(long examId, List<ResultGradeDecorator> resultDecoratorList) throws Exception {
        resultDecoratorList.forEach(rd -> {
            if(rd.getResult().getGradeState() != legalState) {
                throw new RuntimeException(String.format(
                        "CheckGradeStateCommand legal gradeState %s mismatched: examId %d studentId %d gradeState %s",
                                legalState.name(), rd.getExamId(), rd.getStudentId(), rd.getResult().getGradeState()));
            }
        });
    }

    public CheckGradeStateCommand(GradeState legalState) {
        this.legalState = legalState;
    }
}
