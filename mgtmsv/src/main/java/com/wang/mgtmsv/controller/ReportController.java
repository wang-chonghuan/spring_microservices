package com.wang.mgtmsv.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// todo these REST-APIS are simple CRUD, so no implementation
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/report")
public class ReportController {

    /**
     * list all the scores of a student, and average
     */
    @RequestMapping(value="/bystudent", method= RequestMethod.GET)
    public ResponseEntity<?> byStudent(@RequestParam long studentId, @RequestParam long examId) throws Exception {
        return ResponseEntity.ok().body("");
    }

    /**
     * list all the score of a course, and average
     */
    @RequestMapping(value="/bycourse", method= RequestMethod.GET)
    public ResponseEntity<?> byCourse(@RequestParam long studentId, @RequestParam long examId) throws Exception {
        return ResponseEntity.ok().body("");
    }

}
