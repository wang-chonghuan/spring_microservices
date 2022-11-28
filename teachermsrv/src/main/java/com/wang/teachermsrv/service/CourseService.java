package com.wang.teachermsrv.service;


import com.wang.teachermsrv.domain.dto.StudentsExamDTO;
import com.wang.teachermsrv.mq.event.StudentsExamEvent;
import com.wang.teachermsrv.mq.StudentExamPublisher;
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
