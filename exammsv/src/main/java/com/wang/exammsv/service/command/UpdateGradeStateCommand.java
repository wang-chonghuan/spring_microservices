package com.wang.exammsv.service.command;

import com.wang.exammsv.domain.StudentExamResult;

import java.util.List;

public class UpdateGradeStateCommand implements GradeCommand {
    @Override
    public void execute(List<StudentExamResult> resultList) throws BreakChainException {

    }

    public UpdateGradeStateCommand(GradeState gradeState) {
        this.gradeState = gradeState;
    }

    private GradeState gradeState;
}
