package com.wang.exammsv.service.decorator;

import com.wang.exammsv.domain.Question;
import com.wang.exammsv.dto.QuestionSetting;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
public class QuestionSettingDecorator {
    private Question question;
    private QuestionSetting qs;

    public QuestionSettingDecorator(Question q, QuestionSetting qs) {
        this.question = q;
        this.qs = qs;
    }

    // this class is only for combining q and qs then json them to an obj
    public static Map<String, Object> questionDecoratorListToJsonmap(List<QuestionSettingDecorator> questionDecoratorList, String tag) {
        Map<String, Object> jsonmap = new HashMap<>();
        List<Object> jsonlist = new ArrayList<>();
        for(var qd : questionDecoratorList) {
            jsonlist.add(qd);
        }
        jsonmap.put(tag, jsonlist);
        return jsonmap;
    }
}
