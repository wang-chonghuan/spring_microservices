package com.wang.exammsv.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.exammsv.domain.*;
import com.wang.exammsv.dto.AnswerDTO;
import com.wang.exammsv.dto.QuestionSettingDTO;
import com.wang.exammsv.repository.BlankpaperRepository;
import com.wang.exammsv.repository.QuestionRepository;
import com.wang.exammsv.repository.StudentExamResultRepository;
import com.wang.exammsv.command.GradeCommand;
import com.wang.exammsv.dto.QuestionSettingQuestionDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PaperService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private BlankpaperRepository blankpaperRepository;
    @Autowired
    private StudentExamResultRepository resultRepository;

    public void createBlankpaper(QuestionSettingDTO dto) throws JsonProcessingException {
        blankpaperRepository.saveUnique(new Blankpaper(
                dto.getExamId(),
                QuestionSettingQuestionDecorator.listToJson(dto.getExamId(), dto.getQuestionSettingList()
                        .stream()
                        .map(qs -> new QuestionSettingQuestionDecorator(
                                questionRepository.findById(qs.getQuestionId()).get(), qs))
                        .toList())));
    }

    public void saveAnsweredPaper(AnswerDTO dto) {
        resultRepository.updateAnsweredPaper(new StudentExamResult(
                dto.getStudentId(), dto.getExamId(), obj2map(dto), GradeCommand.GradeState.submitted));
    }

    public static String map2str(Map<String, Object> map) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(map);
    }

    public static Map<String, Object> str2map(String str) throws JsonProcessingException {
        return new ObjectMapper().readValue(str, HashMap.class);
    }

    public static Map<String, Object> obj2map(Object obj) {
        return new ObjectMapper().convertValue(obj, Map.class);
    }

    public Map<String,Object> viewAnswers(long studentId, long examId) {
        Optional<StudentExamResult> result = resultRepository.findByStudentIdAndExamId(studentId, examId).stream().findFirst();
        return result.get().getAnsweredpaper();
    }

    public Map<String, Object> fetchBlankPaper(long examId) {
        Optional<Blankpaper> paper = blankpaperRepository.findByExamId(examId).stream().findFirst();
        return paper.get().getPaperContent();
    }
}
