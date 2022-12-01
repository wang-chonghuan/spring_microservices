package com.wang.exammsv.service.command;

import com.wang.exammsv.repository.StudentExamResultRepository;
import com.wang.exammsv.service.decorator.ResultGradeDecorator;

import java.util.List;
import java.util.stream.Collectors;

public class SubmitCommand implements GradeCommand {
    private GradeState gradeState;
    private StudentExamResultRepository resultRepository;

    @Override
    public void execute(long examId, List<ResultGradeDecorator> resultDecoratorList) throws Exception {
        this.resultRepository.saveAll(resultDecoratorList.stream().map(r -> {
            r.getResult().setGradeState(gradeState);
            return r.getResult();
        }).collect(Collectors.toList()));
    }

    public SubmitCommand(GradeState gradeState, StudentExamResultRepository resultRepository) {
        this.gradeState = gradeState;
        this.resultRepository = resultRepository;
    }


}
