package com.wang.onlineexam.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CourseRepository extends CrudRepository<Course, Long> {
    List<Course> findByName(@Param("name") String name);
}
