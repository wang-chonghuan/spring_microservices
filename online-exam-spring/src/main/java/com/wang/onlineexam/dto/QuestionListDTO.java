package com.wang.onlineexam.dto;

import com.wang.onlineexam.service.QuestionWrapper;

import java.util.List;

public class QuestionListDTO {
    private long examId;
    private long studentId;
    private List<QuestionWrapper.Param> paramList;

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

    public List<QuestionWrapper.Param> getParamList() {
        return paramList;
    }

    public void setParamList(List<QuestionWrapper.Param> paramList) {
        this.paramList = paramList;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }
}
