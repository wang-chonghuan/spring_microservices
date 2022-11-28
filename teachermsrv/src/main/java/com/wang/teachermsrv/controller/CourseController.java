package com.wang.teachermsrv.controller;

import com.wang.teachermsrv.domain.dto.StudentsExamDTO;
import com.wang.teachermsrv.service.CourseService;
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

    @Autowired
    private CourseService courseService;

    @RequestMapping(value="/createcourse", method= RequestMethod.POST)
    public ResponseEntity<?> createCourse(@RequestBody Object dto) throws Exception {
        return ResponseEntity.ok().body("");
    }

    @RequestMapping(value="/createexam", method= RequestMethod.POST)
    public ResponseEntity<?> createExam(@RequestBody Object dto) throws Exception {
        return ResponseEntity.ok().body("");
    }

    @RequestMapping(value="/registercourse", method= RequestMethod.POST)
    public ResponseEntity<?> registerCourse(@RequestBody Object dto) throws Exception {
        return ResponseEntity.ok().body("");
    }

    @RequestMapping(value="/registerexam", method= RequestMethod.POST)
    public ResponseEntity<?> registerExam(@RequestBody @Valid StudentsExamDTO studentsExamDTO) {
        courseService.registerStudentToExam(studentsExamDTO);
        return ResponseEntity.ok().body("");
    }
}
