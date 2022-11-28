package com.wang.exammsv;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.exammsv.domain.Question;
import com.wang.exammsv.domain.StudentExamResult;
import com.wang.exammsv.repository.BlankpaperRepository;
import com.wang.exammsv.repository.QuestionRepository;
import com.wang.exammsv.repository.StudentExamResultRepository;
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
    @Autowired
    private QuestionRepository questionRepository;

    public void init() throws JsonProcessingException {
        // set students to exam1
        StudentExamResult studentExamResult1 = new StudentExamResult(1L, 1L);
        StudentExamResult studentExamResult2 = new StudentExamResult(2L, 1L);
        studentExamResultRepository.saveAll(Arrays.asList(studentExamResult1, studentExamResult2));

        // set a new record to Question
        // conver json to java format string by using this website: https://tools.knowledgewalls.com/json-to-string
        Map<String, Object> questionStatement1 = new ObjectMapper().readValue(
                "{\"statement\":\"which year is golang first released?\",\"choices\":[\"A. 2010\",\"B. 2007\",\"C. 2012\",\"D. 2017\",\"E. 2018\"]}", HashMap.class);
        String refAnswer1 = "B";
        Question question1 = new Question(Question.QuestionType.SINGLE, questionStatement1, refAnswer1, "golang");
        Map<String, Object> questionStatement2 = new ObjectMapper().readValue(
                "{\"statement\":\"which year is NOT golang first released?\",\"choices\":[\"A. 2010\",\"B. 2011\",\"C. 2007\",\"D. 2017\"]}", HashMap.class);
        String refAnswer2 = "A,B,D";
        Question question2 = new Question(Question.QuestionType.MULTIPLE, questionStatement2, refAnswer2, "golang");
        Map<String, Object> questionStatement3 = new ObjectMapper().readValue(
                "{\"statement\":\"Who created golang?\"}", HashMap.class);
        String refAnswer3 = "Robert Griesemer, Rob Pike, and Ken Thompson";
        Question question3 = new Question(Question.QuestionType.WRITING, questionStatement3, refAnswer3, "golang,programming language");
        questionRepository.saveAll(Arrays.asList(question1, question2, question3));
    }
}
