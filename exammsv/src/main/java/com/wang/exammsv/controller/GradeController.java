package com.wang.exammsv.controller;

import com.wang.exammsv.domain.Blankpaper;
import com.wang.exammsv.domain.StudentExamResult;
import com.wang.exammsv.repository.StudentExamResultRepository;
import com.wang.exammsv.service.GradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;
    @Autowired
    private StudentExamResultRepository resultRepository;

    @RequestMapping(value="/viewanswers", method= RequestMethod.GET)
    public ResponseEntity<?> viewAnswers(@RequestParam long studentId, @RequestParam long examId) throws Exception {
        Optional<StudentExamResult> result = resultRepository.findByStudentIdAndExamId(studentId, examId).stream().findFirst();
        var ret = result.get().getAnsweredpaper();
        return ResponseEntity.ok().body(result.get().getAnsweredpaper());
    }

    @RequestMapping(value="/manual", method= RequestMethod.POST)
    public ResponseEntity<?> manual(@RequestBody Object dto) throws Exception {
        return ResponseEntity.ok().body("");
    }

    @RequestMapping(value="/auto", method= RequestMethod.GET)
    public ResponseEntity<?> auto(@RequestParam long examId) throws Exception {
        gradeService.autoGrade(examId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // modify all the scores of an exam, with an expression like: 50 10 0.6 + *
    @RequestMapping(value="/addbonus", method= RequestMethod.POST)
    public ResponseEntity<?> addBonus(@RequestBody Object dto) throws Exception {
        return ResponseEntity.ok().body("");
    }

    // send score to mgt-sv to update the scores there
    @RequestMapping(value="/broadcastscores", method= RequestMethod.GET)
    public ResponseEntity<?> broadcastScores(@RequestParam long examId) throws Exception {
        gradeService.broadcastScores(examId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
