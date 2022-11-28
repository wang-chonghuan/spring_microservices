package com.wang.mgtmsv.repository;

import com.wang.mgtmsv.domain.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    List<Teacher> findByEmail(String s);
}
