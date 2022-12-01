package com.wang.exammsv.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssembledAnswer {
    private Answer answer;
    private QuestionPOJO questionPOJO;
    private double score;
}
