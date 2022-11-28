package com.wang.exammsv.service;

import com.wang.exammsv.domain.StudentExamResult;
import com.wang.exammsv.domain.dto.AnswersDTO;
import com.wang.exammsv.repository.StudentExamResultRepository;
import com.wang.exammsv.utils.AnyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService {
    @Autowired
    private StudentExamResultRepository studentExamResultRepository;
    public void saveAnsweredPaper(AnswersDTO dto) {
        var studentExamResult = new StudentExamResult(dto.getStudentId(), dto.getExamId());
        studentExamResult.setAnsweredpaper(AnyUtil.objectToJsonmap(dto));
        studentExamResultRepository.save(studentExamResult);
    }
}
