package com.wang.exammsv.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionSetting {
    private long questionId;
    private int order;
    private double mark;
}
