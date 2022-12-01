package com.wang.mgtmsv.domain;

import com.wang.mgtmsv.domain.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findByEmail(String s);
}
