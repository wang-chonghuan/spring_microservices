package com.wang.exammsv.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {
    private long examId;
    private long studentId;
    private List<Answer> answerList;
}
