package com.wang.exammsv.service.command;

import com.wang.exammsv.domain.StudentExamResult;

import java.util.List;

public class CheckGradeStateCommand implements GradeCommand {

    private GradeState shouldbeState;

    @Override
    public void execute(long examId, List<StudentExamResult> resultList) throws BreakChainException {

    }

    public CheckGradeStateCommand(GradeState shouldbeState) {
        this.shouldbeState = shouldbeState;
    }
}
