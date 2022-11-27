package com.wang.teachermsrv.repository;

import com.wang.teachermsrv.domain.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.Arrays;
import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Long> {
    List<Course> findByName(String s);
}
