package com.wang.teachermsrv.repository;

import com.wang.teachermsrv.domain.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.Arrays;
import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findByEmail(String s);
}
