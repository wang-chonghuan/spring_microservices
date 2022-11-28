package com.wang.exammsv.service;

import com.wang.exammsv.domain.Question;
import com.wang.exammsv.domain.dto.QuestionSetting;
import lombok.Data;

import java.util.*;

@Data
public class QuestionDecorator {
    final private Question question;
    final private QuestionSetting questionSetting;

    public Map<String, Object> toJsonmap() {
        Map<String, Object> jsonmap = new Hashtable<>();
        jsonmap.put("question_id", question.getId());
        jsonmap.put("type", question.getQuestionType());
        jsonmap.put("ref_answer", question.getRefAnswer());
        jsonmap.put("content", question.getQuestionContent());
        jsonmap.put("order", questionSetting.getOrder());
        jsonmap.put("mark", questionSetting.getMark());
        // no jsonmap.put("score", );
        // no jsonmap.put("answer", );
        return jsonmap;
    }

    public static Map<String, Object> questionDecoratorListToJsonmap(List<QuestionDecorator> questionDecoratorList, String tag) {
        Map<String, Object> jsonmap = new HashMap<>();
        List<Object> jsonlist = new ArrayList<>();
        for(QuestionDecorator qd : questionDecoratorList) {
            jsonlist.add(qd);
        }
        jsonmap.put(tag, jsonlist);
        return jsonmap;
    }
}
