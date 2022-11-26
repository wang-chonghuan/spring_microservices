package com.wang.onlineexam.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wang.onlineexam.domain.Exam;
import com.wang.onlineexam.domain.StudentExamRelation;
import com.wang.onlineexam.dto.QuestionListDTO;
import com.wang.onlineexam.service.PaperService;
import com.wang.onlineexam.utils.AnyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ExamController {
    @Autowired
    PaperService paperService;

    private static final Logger logger = LoggerFactory.getLogger(ExamController.class);

    @RequestMapping(value="/create-blank-paper", method= RequestMethod.POST)
    public ResponseEntity<?> createBlankPaper(@RequestBody QuestionListDTO req) throws JsonProcessingException {
        Exam exam = paperService.createBlankPaper(req.getExamId(), req.getParamList());
        String blankPaperJsonstr = AnyUtil.jsonmapToJsonstr(exam.getBlankPaper());
        //logger.info(String.format("createBlankPaper:: examId: %d updated with blankPaper: %s", exam.getId(), blankPaperJsonstr));
        // 200 is the appropriate status code assuming the PUT only did an update and did not create a resource.
        return ResponseEntity.ok().body(blankPaperJsonstr);
    }

    @RequestMapping(value="/create-answered-paper", method= RequestMethod.POST)
    public ResponseEntity<?> createAnsweredPaper(@RequestBody QuestionListDTO req) throws JsonProcessingException {
        StudentExamRelation rel = paperService.createAnsweredPaper(req.getStudentId(), req.getExamId(), req.getParamList());
        String answeredPaperJsonstr = AnyUtil.jsonmapToJsonstr(rel.getPaperAnswered());
        return ResponseEntity.ok().body(answeredPaperJsonstr);
    }

    @RequestMapping(value="/fetch-writing-questions", method= RequestMethod.GET)
    public ResponseEntity<?> fetchWritingQuestions(@RequestParam long studentId, @RequestParam long examId) throws Exception {
        Map<String, Object> writingQuestionsJsonmap = paperService.fetchWritingQuestions(studentId, examId);
        String writingQuestionsJsonstr = AnyUtil.jsonmapToJsonstr(writingQuestionsJsonmap);
        //logger.info(String.format("fetchWritingQuestions returns: %s", writingQuestionsJsonstr));
        return ResponseEntity.ok().body(writingQuestionsJsonstr);
    }

    @RequestMapping(value="/update-writing-scores", method= RequestMethod.POST)
    public ResponseEntity<?> updateWritingScores(@RequestBody QuestionListDTO req) throws Exception {
        StudentExamRelation rel = paperService.updateWritingScores(req.getStudentId(), req.getExamId(), req.getParamList());
        return ResponseEntity.ok().body(rel.getScore());
    }
}


