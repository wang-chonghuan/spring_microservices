package com.wang.exammsv.service.command;

import com.wang.exammsv.domain.StudentExamResult;

import java.util.List;

public interface GradeCommand {
    public void execute(long examId, List<StudentExamResult> resultList) throws BreakChainException;

    public enum GradeState {
        pending, submitted, assembled, partialscored, fullyscored, broadcasted
    }
}
