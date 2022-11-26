package com.wang.onlineexam.web;

import com.wang.onlineexam.domain.Teacher;
import com.wang.onlineexam.domain.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController {
    @Autowired
    TeacherRepository teacherRepository;

    @RequestMapping(value = "/create-teacher", method = RequestMethod.POST)
    public ResponseEntity<?> createTeacher(@RequestBody Teacher.ReqCreateTeacher reqCreateTeacher) {
        teacherRepository.save(new Teacher(reqCreateTeacher.getName(), reqCreateTeacher.getEmail()));
        // no ResponseEntity.ok().build(), this is 200, which is wrong here
        return new ResponseEntity(HttpStatus.CREATED); // should be 201 for posting success
    }

    @RequestMapping(value="/all-teachers", method=RequestMethod.GET)
    public Iterable<Teacher> getCars() {
        return teacherRepository.findAll();
    }
}
