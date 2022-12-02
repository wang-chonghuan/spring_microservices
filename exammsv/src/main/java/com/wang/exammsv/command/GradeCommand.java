package com.wang.exammsv.command;

import com.wang.exammsv.dto.AssembledAnswerResultDecorator;

import java.util.List;

public interface GradeCommand {
    public void execute(long examId, List<AssembledAnswerResultDecorator> resultDecoratorList) throws Exception;

    public enum GradeState {
        // autograded is used only when manually grade implemented, now its unused
        pending, submitted, autograded, fullygraded, broadcasted
    }
}
