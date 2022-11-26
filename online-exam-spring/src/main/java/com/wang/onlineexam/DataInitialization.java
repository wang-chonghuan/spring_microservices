package com.wang.onlineexam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.onlineexam.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class DataInitialization {
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(DataInitialization.class);
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private StudentExamRelationRepository studentExamRelationRepository;

    // insert basic records for developing and testing
    public void init() throws JsonProcessingException {
        // insert teachers
        Teacher teacher1 = new Teacher("teacher1.name", "teacher1.email");
        Teacher teacher2 = new Teacher("teacher2.name", "teacher2.email");
        teacherRepository.saveAll(Arrays.asList(teacher1, teacher2));

        // insert students
        Student student1 = new Student("student1.name", "student1.email");
        Student student2 = new Student("student2.name", "student2.email");
        studentRepository.saveAll(Arrays.asList(student1, student2));

        // get teacher and students from db for setting to other entities
        List<Teacher> queryTeacherList = teacherRepository.findByEmail("teacher1.email");
        Optional<Teacher> queryTeacher1 = queryTeacherList.stream().findFirst();
        long idTeacher1 = queryTeacher1.get().getId();
        Student queryStudent1 = studentRepository.findByEmail("student1.email").stream().findFirst().get();
        Student queryStudent2 = studentRepository.findByEmail("student2.email").stream().findFirst().get();

        // insert course1
        Course course1 = new Course("CS5741-22", Course.CourseStatus.ACTIVE, teacherRepository.findById(idTeacher1).get());
        course1.getStudents().add(queryStudent1); // autogen record in table StudentCourseRelation
        course1.getStudents().add(queryStudent2);
        courseRepository.save(course1);

        // insert exam1 to course1
        LocalDateTime examTime = LocalDateTime.of(2022, 12, 31, 18, 59, 59);
        Course queryCourse1 = courseRepository.findByName("CS5741-22").stream().findFirst().get();
        Exam exam1withoutStudentsAndPaper = new Exam(
                queryCourse1, "CS5741-midtern-exam", "10% of the final score",
                "CS025", LocalDateTime.now(), examTime, 2400, Exam.ExamStatus.SETTING);
        examRepository.save(exam1withoutStudentsAndPaper);
        exam1withoutStudentsAndPaper.setLocation("CS429");
        Exam queryExam1 = examRepository.save(exam1withoutStudentsAndPaper);

        // set students to exam1
        StudentExamRelation studentExamRelation1 = new StudentExamRelation(queryStudent1, queryExam1);
        StudentExamRelation studentExamRelation2 = new StudentExamRelation(queryStudent2, queryExam1);
        studentExamRelationRepository.save(studentExamRelation1);
        studentExamRelationRepository.save(studentExamRelation2);
        //studentExamRelation1.setScore(9.0);
        studentExamRelationRepository.save(studentExamRelation1);
        //studentExamRelationRepository.saveAll(Arrays.asList(studentExamRelation1, studentExamRelation2));

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
