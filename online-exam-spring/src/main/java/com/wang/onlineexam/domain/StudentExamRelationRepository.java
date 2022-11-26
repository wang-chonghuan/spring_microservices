package com.wang.onlineexam.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface StudentExamRelationRepository extends CrudRepository<StudentExamRelation, Long> {
    List<StudentExamRelation> findByStudentIdAndExamId(@Param("student_id")long studentId, @Param("exam_id")long examId);
}
