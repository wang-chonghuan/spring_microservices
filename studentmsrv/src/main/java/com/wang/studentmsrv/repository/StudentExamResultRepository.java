package com.wang.studentmsrv.repository;

import com.wang.studentmsrv.domain.Blankpaper;
import com.wang.studentmsrv.domain.StudentExamResult;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentExamResultRepository extends CrudRepository<StudentExamResult, Long> {
    List<StudentExamResult> findByExamId(long examId);
}
