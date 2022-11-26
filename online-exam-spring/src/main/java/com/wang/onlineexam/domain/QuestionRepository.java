package com.wang.onlineexam.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface QuestionRepository extends CrudRepository<Question, Long> {
    // TODO!!! list all the questions whose tags has golong
    //@Query("select c from Question c where c.tags = '%golang%'")
    //List<Question> findByTags(String tags);
}
