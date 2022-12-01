package com.wang.exammsv.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    private long questionId;
    private int order;
    private double mark;
    private String answer;
}
