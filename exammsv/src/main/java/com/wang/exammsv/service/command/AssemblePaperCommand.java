package com.wang.exammsv.service.command;

import com.wang.exammsv.domain.StudentExamResult;
import com.wang.exammsv.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AssemblePaperCommand implements GradeCommand {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void execute(List<StudentExamResult> resultList) throws BreakChainException {

    }
}
