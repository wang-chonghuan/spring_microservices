package com.wang.exammsv.repository;

import com.wang.exammsv.domain.Blankpaper;
import com.wang.exammsv.domain.StudentExamResult;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentExamResultRepository extends CrudRepository<StudentExamResult, Long> {
    List<StudentExamResult> findByExamId(long examId);
}
