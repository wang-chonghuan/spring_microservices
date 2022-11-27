package com.wang.studentmsrv;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.studentmsrv.domain.StudentExamResult;
import com.wang.studentmsrv.repository.BlankpaperRepository;
import com.wang.studentmsrv.repository.StudentExamResultRepository;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class InitData {

    @Autowired
    private StudentExamResultRepository studentExamResultRepository;
    @Autowired
    private BlankpaperRepository blankpaperRepository;

    public void init() throws JsonProcessingException {
        // set students to exam1
        StudentExamResult studentExamResult1 = new StudentExamResult(1L, 1L);
        StudentExamResult studentExamResult2 = new StudentExamResult(2L, 1L);
        studentExamResultRepository.saveAll(Arrays.asList(studentExamResult1, studentExamResult2));
    }
}
