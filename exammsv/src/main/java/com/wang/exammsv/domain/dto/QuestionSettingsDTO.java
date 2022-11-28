package com.wang.exammsv.domain.dto;

import lombok.Value;

import java.util.List;

@Value
public class QuestionSettingsDTO {
    long examId;
    List<QuestionSetting> questionSettingList;
}
