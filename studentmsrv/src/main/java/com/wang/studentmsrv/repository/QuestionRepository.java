package com.wang.studentmsrv.repository;

import com.wang.studentmsrv.domain.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {
}
