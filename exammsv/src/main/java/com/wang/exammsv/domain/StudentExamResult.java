package com.wang.exammsv.domain;

import com.vladmihalcea.hibernate.type.json.JsonType;
import com.wang.exammsv.service.command.GradeCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.text.DecimalFormat;
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
    private Long studentId; // fk of Student in mgtmsv
    private Long examId; // fk of Exam in mgtmsv
    private double score;

    public void setScore(double score) {
        this.score = Double.parseDouble(new DecimalFormat("#.00").format(score));
    }

    @Type(type = "json")
    @Column(name = "answered_paper", columnDefinition = "json")
    private Map<String, Object> answeredpaper = new HashMap<>();

    @Type(type = "json")
    @Column(name = "assembled_paper", columnDefinition = "json")
    private Map<String, Object> assembledpaper = new HashMap<>();

    @Enumerated(EnumType.ORDINAL)
    private GradeCommand.GradeState gradeState;

    // when create result, it is pending
    public StudentExamResult(Long studentId, Long examId) {
        this.studentId = studentId;
        this.examId = examId;
        this.gradeState = GradeCommand.GradeState.pending; // first create the result, the student hasnt taken the exam
    }
}
