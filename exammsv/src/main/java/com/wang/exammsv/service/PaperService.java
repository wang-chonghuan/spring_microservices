package com.wang.exammsv.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wang.exammsv.domain.Blankpaper;
import com.wang.exammsv.domain.Question;
import com.wang.exammsv.domain.dto.QuestionSetting;
import com.wang.exammsv.domain.dto.QuestionSettingsDTO;
import com.wang.exammsv.repository.BlankpaperRepository;
import com.wang.exammsv.repository.QuestionRepository;
import com.wang.exammsv.service.question.QuestionDecorator;
import com.wang.exammsv.service.question.QuestionSettingDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PaperService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private BlankpaperRepository blankpaperRepository;

    public void createBlankpaper(QuestionSettingsDTO dto) throws JsonProcessingException {

        List<QuestionSettingDecorator> qdList = new ArrayList<>();

        for(QuestionSetting qs: dto.getQuestionSettingList()) {
            Question q = questionRepository.findById(qs.questionId).get();
            QuestionSettingDecorator qd = new QuestionSettingDecorator(q, qs);
            qdList.add(qd);
        }

        Map<String, Object> qListJsonmap = QuestionSettingDecorator.questionDecoratorListToJsonmap(
                qdList, QuestionListTag.blank_question_list.name());
        // aString = anIntList.stream().map(String::valueOf).collect(Collectors.joining(","))

        Blankpaper b = new Blankpaper(dto.getExamId(), qListJsonmap);
        blankpaperRepository.save(b);
    }

    public enum QuestionListTag {
        blank_question_list, answered_question_list, writing_question_list
    }
}
