package com.wang.mgtmsv.domain;

import com.wang.mgtmsv.domain.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Long> {
    List<Course> findByName(String s);
}
