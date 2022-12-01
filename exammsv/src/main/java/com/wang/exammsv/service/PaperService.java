package com.wang.exammsv.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wang.exammsv.domain.Blankpaper;
import com.wang.exammsv.domain.Question;
import com.wang.exammsv.dto.QuestionSetting;
import com.wang.exammsv.dto.QuestionSettingDTO;
import com.wang.exammsv.domain.BlankpaperRepository;
import com.wang.exammsv.domain.QuestionRepository;
import com.wang.exammsv.service.decorator.QuestionSettingDecorator;
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

    public void createBlankpaper(QuestionSettingDTO dto) throws JsonProcessingException {

        List<QuestionSettingDecorator> qdList = new ArrayList<>();

        for(QuestionSetting qs: dto.getQuestionSettingList()) {
            Question q = questionRepository.findById(qs.getQuestionId()).get();
            QuestionSettingDecorator qd = new QuestionSettingDecorator(q, qs);
            qdList.add(qd);
        }

        Map<String, Object> qListJsonmap = QuestionSettingDecorator
                .questionDecoratorListToJsonmap(qdList, "blank_question_list");

        Blankpaper b = new Blankpaper(dto.getExamId(), qListJsonmap);
        blankpaperRepository.save(b);
    }
}
