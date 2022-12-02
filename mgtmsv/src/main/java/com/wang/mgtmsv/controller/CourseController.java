package com.wang.mgtmsv.controller;

import com.wang.mgtmsv.domain.dto.StudentsExamDTO;
import com.wang.mgtmsv.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController {

    /**
     * this is the only REST-API implemented in mgt-microservice
     * the data it needs is initialized in the InitData.java
     * function: register the students to take a specific exam, by sending an event to the exam-microservice
     * @param studentsExamDTO
     * @return
     */
    @RequestMapping(value="/registerexam", method= RequestMethod.POST)
    public ResponseEntity<?> registerExam(@RequestBody @Valid StudentsExamDTO studentsExamDTO) {
        courseService.registerStudentToExam(studentsExamDTO);
        return ResponseEntity.ok().body("");
    }

    // todo these REST-APIS are simple CRUD, so no implementation
    @Autowired
    private CourseService courseService;

    // todo these REST-APIS are simple CRUD, so no implementation
    @RequestMapping(value="/createcourse", method= RequestMethod.POST)
    public ResponseEntity<?> createCourse(@RequestBody Object dto) throws Exception {
        return ResponseEntity.ok().body("");
    }

    // todo these REST-APIS are simple CRUD, so no implementation
    @RequestMapping(value="/createexam", method= RequestMethod.POST)
    public ResponseEntity<?> createExam(@RequestBody Object dto) throws Exception {
        return ResponseEntity.ok().body("");
    }

    // todo these REST-APIS are simple CRUD, so no implementation
    @RequestMapping(value="/registercourse", method= RequestMethod.POST)
    public ResponseEntity<?> registerCourse(@RequestBody Object dto) throws Exception {
        return ResponseEntity.ok().body("");
    }
}
