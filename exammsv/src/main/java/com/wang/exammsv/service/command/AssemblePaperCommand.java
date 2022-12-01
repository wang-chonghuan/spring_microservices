package com.wang.exammsv.service.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.exammsv.domain.StudentExamResult;
import com.wang.exammsv.dto.AnswerDTO;
import com.wang.exammsv.dto.AssembledAnswer;
import com.wang.exammsv.dto.AssembledAnswerDTO;
import com.wang.exammsv.dto.QuestionPOJO;
import com.wang.exammsv.repository.QuestionRepository;
import com.wang.exammsv.repository.StudentExamResultRepository;
import com.wang.exammsv.utils.AnyUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AssemblePaperCommand implements GradeCommand {

    private QuestionRepository questionRepository;
    private StudentExamResultRepository resultRepository;

    public AssemblePaperCommand(QuestionRepository questionRepository, StudentExamResultRepository resultRepository) {
        this.questionRepository = questionRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public void execute(long examId, List<StudentExamResult> resultList) throws BreakChainException {
        for(var result : resultList) {
            AnswerDTO answerDTO = new ObjectMapper().convertValue(result.getAnsweredpaper(), AnswerDTO.class);
            if(answerDTO.getAnswerList() == null) {
                continue;
            }
            var assembledAnswerList = new ArrayList<AssembledAnswer>();
            for(var answer : answerDTO.getAnswerList()) {
                var q = questionRepository.findById(answer.getQuestionId()).get();
                var questionPOJO = new QuestionPOJO(q.getId(), q.getRefAnswer(), q.getQuestionType(), q.getQuestionContent());
                var assembledAnswer = new AssembledAnswer(answer, questionPOJO, 0.0);
                assembledAnswerList.add(assembledAnswer);
            }
            var assembledAnswerDTO = new AssembledAnswerDTO(examId, result.getStudentId(), assembledAnswerList);
            result.setAssembledpaper(AnyUtil.objectToJsonmap(assembledAnswerDTO));
        }
        resultRepository.saveAll(resultList);
    }
}
