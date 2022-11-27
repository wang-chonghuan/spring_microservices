package com.wang.studentmsrv.repository;

import com.wang.studentmsrv.domain.Blankpaper;
import com.wang.studentmsrv.domain.StudentExamResult;
import org.springframework.data.repository.CrudRepository;

public interface StudentExamResultRepository extends CrudRepository<StudentExamResult, Long> {
}
