package com.wang.exammsv.dto;

import com.wang.exammsv.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionPOJO {
    private long questionId;
    private String refAnswer;
    private Question.QuestionType questionType;
    private Map<String,Object> questionContent;
}
