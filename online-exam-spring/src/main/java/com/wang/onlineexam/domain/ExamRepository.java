package com.wang.onlineexam.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ExamRepository extends CrudRepository<Exam, Long> {
    List<Exam> findByTitle(@Param("title") String title);
}
