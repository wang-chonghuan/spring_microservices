package com.wang.exammsv.service;

import com.wang.exammsv.domain.StudentExamResult;
import com.wang.exammsv.dto.AnswerDTO;
import com.wang.exammsv.domain.StudentExamResultRepository;
import com.wang.exammsv.utils.AnyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExamService {
    @Autowired
    private StudentExamResultRepository resultRepository;
    public void saveAnsweredPaper(AnswerDTO dto) {
        var result = resultRepository
                .findByStudentIdAndExamId(dto.getStudentId(), dto.getExamId())
                .stream().findFirst();
        if(result.isEmpty()) {
            result = Optional.of(new StudentExamResult(dto.getStudentId(), dto.getExamId()));
        }
        result.get().setAnsweredpaper(AnyUtil.objectToJsonmap(dto));
        resultRepository.save(result.get());
    }
}
