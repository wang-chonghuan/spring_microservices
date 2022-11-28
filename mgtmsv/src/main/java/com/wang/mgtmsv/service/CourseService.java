package com.wang.mgtmsv.service;


import com.wang.mgtmsv.domain.dto.StudentsExamDTO;
import com.wang.mgtmsv.mq.event.StudentsExamEvent;
import com.wang.mgtmsv.mq.StudentExamPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final StudentExamPublisher studentExamPublisher;

    public void registerStudentToExam(StudentsExamDTO dto) {
        studentExamPublisher.publish(new StudentsExamEvent(dto.getExamId(), dto.getStudentIdList()));
    }
}
