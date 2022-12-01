package com.wang.exammsv.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManuallyGradeDTO {
    private long examId;
    private long studentId;
    List<ManullyGrade> manullyGradeList;
}
