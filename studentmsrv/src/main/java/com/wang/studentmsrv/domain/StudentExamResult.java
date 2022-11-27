package com.wang.studentmsrv.domain;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;

@TypeDefs({@TypeDef(name = "json", typeClass = JsonType.class)})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentExamResult {
    @Id
    @GeneratedValue
    private Long id;
    private Long studentId; // fk of Student in teachermsrv
    private Long examId; // fk of Exam in teachermsrv
    private double score;
    @Type(type = "json")
    @Column(name = "answered_paper", columnDefinition = "json")
    private Map<String, Object> answeredpaper = new HashMap<>();

    public StudentExamResult(Long studentId, Long examId) {
        this.studentId = studentId;
        this.examId = examId;
    }
}
