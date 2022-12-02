package com.wang.exammsv.domain;

import com.sun.istack.NotNull;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@TypeDefs({@TypeDef(name = "json", typeClass = JsonType.class)})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    private String refAnswer;
    private String tags; // one decorator has many tags, can be selected by regular-expressions

    private QuestionType questionType;

    public Question(long questionId,
                    QuestionType questionType,
                    Map<String, Object> questionContent,
                    String refAnswer,
                    String tags) {
        this.id = questionId;
        this.questionType = questionType;
        this.questionContent = questionContent;
        this.refAnswer = refAnswer;
        this.tags = tags;
    }

    public enum QuestionType {
        SINGLE, MULTIPLE, WRITING
    }
    @Type(type = "json")
    @Column(name = "question_content", columnDefinition = "json")
    private Map<String,Object> questionContent = new HashMap<>();
}
