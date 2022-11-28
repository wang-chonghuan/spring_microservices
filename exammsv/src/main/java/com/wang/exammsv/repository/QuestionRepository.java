package com.wang.exammsv.repository;

import com.wang.exammsv.domain.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {
}
