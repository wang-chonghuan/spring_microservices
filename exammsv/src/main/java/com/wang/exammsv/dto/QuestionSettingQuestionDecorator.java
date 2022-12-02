package com.wang.exammsv.dto;

import com.wang.exammsv.domain.Question;
import lombok.Data;

import java.util.*;

@Data
public class QuestionSettingQuestionDecorator {
    private final Question question;
    private final QuestionSetting qs;

    public QuestionSettingQuestionDecorator(Question q, QuestionSetting qs) {
        this.question = q;
        this.qs = qs;
    }

    // this class is only for combining q and qs then json them to an obj
    public static Map<String, Object> listToJson(long examId, List<QuestionSettingQuestionDecorator> questionDecoratorList) {
        Map<String, Object> jsonmap = new HashMap<>();
        List<Object> jsonlist = new ArrayList<>(questionDecoratorList);
        jsonmap.put("examId", examId);
        jsonmap.put("blankPaperContent", jsonlist);
        return jsonmap;
    }
}
