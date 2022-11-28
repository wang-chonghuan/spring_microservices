package com.wang.exammsv.controller;

import com.wang.exammsv.service.GradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @RequestMapping(value="/viewanswers", method= RequestMethod.GET)
    public ResponseEntity<?> viewpaper(@RequestParam long studentId, @RequestParam long examId) throws Exception {
        return ResponseEntity.ok().body("");
    }

    @RequestMapping(value="/manual", method= RequestMethod.POST)
    public ResponseEntity<?> manual(@RequestBody Object dto) throws Exception {
        return ResponseEntity.ok().body("");
    }

    @RequestMapping(value="/auto", method= RequestMethod.GET)
    public ResponseEntity<?> auto(@RequestParam long studentId, @RequestParam long examId) throws Exception {
        return ResponseEntity.ok().body("");
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
