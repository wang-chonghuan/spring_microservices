package com.wang.exammsv.controller;

import com.wang.exammsv.domain.StudentExamResult;
import com.wang.exammsv.dto.BroadcastDTO;
import com.wang.exammsv.repository.StudentExamResultRepository;
import com.wang.exammsv.dto.ManuallyGradeDTO;
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

    @RequestMapping(value="/auto", method= RequestMethod.GET)
    public ResponseEntity<?> auto(@RequestParam long examId) throws Exception {
        gradeService.autoGrade(examId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 1. add bonus(or "") to all scores
    // 2. send score to mgt-sv to update the scores there
    // modify all the scores of an exam, with an expression like: "10 + 1.2 * 1 +"
    // apply this to 50 : (50+10)*1.2+1=73
    @RequestMapping(value="/broadcastscores", method= RequestMethod.POST)
    public ResponseEntity<?> broadcastScoreWithBonus(@RequestBody BroadcastDTO broadcastDTO) throws Exception {
        gradeService.broadcastScoreWithBonus(broadcastDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // todo this one is trivial, leave it unimplemented, we default that all the question are graded automatically
    @RequestMapping(value="/manual", method= RequestMethod.POST)
    public ResponseEntity<?> manual(@RequestBody ManuallyGradeDTO dto) throws Exception {
        gradeService.manuallyGrade(dto);
        return ResponseEntity.ok().body("");
    }
}
