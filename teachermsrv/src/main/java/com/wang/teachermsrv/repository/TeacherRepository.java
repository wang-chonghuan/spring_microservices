package com.wang.teachermsrv.repository;

import com.wang.teachermsrv.domain.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    List<Teacher> findByEmail(String s);
}
