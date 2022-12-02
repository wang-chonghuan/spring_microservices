package com.wang.exammsv.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wang.exammsv.domain.*;
import com.wang.exammsv.dto.AnswerDTO;
import com.wang.exammsv.dto.QuestionSettingDTO;
import com.wang.exammsv.service.command.GradeCommand;
import com.wang.exammsv.service.decorator.QuestionSettingDecorator;
import com.wang.exammsv.utils.AnyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private BlankpaperRepository blankpaperRepository;
    @Autowired
    private StudentExamResultRepository resultRepository;

    public void createBlankpaper(QuestionSettingDTO dto) throws JsonProcessingException {
        blankpaperRepository.saveUnique(new Blankpaper(
                dto.getExamId(),
                QuestionSettingDecorator.listToJson(dto.getExamId(), dto.getQuestionSettingList()
                        .stream()
                        .map(qs -> new QuestionSettingDecorator(
                                questionRepository.findById(qs.getQuestionId()).get(), qs))
                        .toList())));
    }

    public void saveAnsweredPaper(AnswerDTO dto) {
        resultRepository.updateAnsweredPaper(new StudentExamResult(
                dto.getStudentId(), dto.getExamId(),
                AnyUtil.objectToJsonmap(dto), GradeCommand.GradeState.submitted));
    }
}
