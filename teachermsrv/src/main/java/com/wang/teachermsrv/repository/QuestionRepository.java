package com.wang.teachermsrv.repository;

import com.wang.teachermsrv.domain.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {
}
