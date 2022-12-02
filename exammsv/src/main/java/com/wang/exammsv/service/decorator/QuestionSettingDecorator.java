package com.wang.exammsv.service.decorator;

import com.wang.exammsv.domain.Question;
import com.wang.exammsv.dto.QuestionSetting;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
public class QuestionSettingDecorator {
    private final Question question;
    private final QuestionSetting qs;

    public QuestionSettingDecorator(Question q, QuestionSetting qs) {
        this.question = q;
        this.qs = qs;
    }

    // this class is only for combining q and qs then json them to an obj
    public static Map<String, Object> listToJson(long examId, List<QuestionSettingDecorator> questionDecoratorList) {
        Map<String, Object> jsonmap = new HashMap<>();
        List<Object> jsonlist = new ArrayList<>(questionDecoratorList);
        jsonmap.put("examId", examId);
        jsonmap.put("blankPaperContent", jsonlist);
        return jsonmap;
    }
}
