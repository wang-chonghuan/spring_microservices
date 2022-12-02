package com.wang.exammsv.service.command;

import java.util.List;

public interface GradeCommand {
    public void execute(long examId, List<ResultGradeDecorator> resultDecoratorList) throws Exception;

    public enum GradeState {
        // autograded is used only when manually grade implemented, now its unused
        pending, submitted, autograded, fullygraded, broadcasted
    }
}
