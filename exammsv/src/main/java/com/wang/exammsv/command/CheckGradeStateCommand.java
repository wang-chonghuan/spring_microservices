package com.wang.exammsv.command;

import com.wang.exammsv.dto.AssembledAnswerResultDecorator;

import java.util.List;

public class CheckGradeStateCommand implements GradeCommand {

    private final GradeState legalState;

    @Override
    public void execute(long examId, List<AssembledAnswerResultDecorator> resultDecoratorList) throws Exception {
        resultDecoratorList.forEach(rd -> {
            if(rd.getResult().getGradeState() != legalState) {
                throw new RuntimeException(String.format(
                        "CheckGradeStateCommand: db state: examId %d studentId %d gradeState %s, shoule be state: %s",
                                rd.getExamId(), rd.getStudentId(), rd.getResult().getGradeState(), legalState.name()));
            }
        });
    }

    public CheckGradeStateCommand(GradeState legalState) {
        this.legalState = legalState;
    }
}
