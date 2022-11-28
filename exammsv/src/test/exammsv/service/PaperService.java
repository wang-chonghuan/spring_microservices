package com.wang.exammsv.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wang.exammsv.domain.Blankpaper;
import com.wang.exammsv.domain.Question;
import com.wang.exammsv.domain.dto.QuestionSetting;
import com.wang.exammsv.domain.dto.QuestionSettingsDTO;
import com.wang.exammsv.repository.BlankpaperRepository;
import com.wang.exammsv.repository.QuestionRepository;
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

        List<QuestionDecorator> qdList = new ArrayList<>();

        for(QuestionSetting qs: dto.getQuestionSettingList()) {
            Question q = questionRepository.findById(qs.getQuestionId()).get();
            QuestionDecorator qd = new QuestionDecorator(q, qs);
            qdList.add(qd);
        }

        Map<String, Object> qListJsonmap = QuestionDecorator.questionDecoratorListToJsonmap(
                qdList, QuestionListTag.blank_question_list.name());
        // aString = anIntList.stream().map(String::valueOf).collect(Collectors.joining(","))

        Blankpaper b = new Blankpaper(dto.getExamId(), qListJsonmap);
        blankpaperRepository.save(b);
    }

    public enum QuestionListTag {
        blank_question_list, answered_question_list, writing_question_list
    }
}
