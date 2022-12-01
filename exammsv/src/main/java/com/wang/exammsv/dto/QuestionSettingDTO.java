package com.wang.exammsv.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionSettingDTO {
    private long examId;
    private List<QuestionSetting> questionSettingList;
}
