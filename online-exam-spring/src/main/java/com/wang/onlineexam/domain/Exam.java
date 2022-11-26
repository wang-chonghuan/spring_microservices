package com.wang.onlineexam.domain;

import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * for using JSON here
 * <dependency>
 * 	<groupId>com.vladmihalcea</groupId>
 * 	<artifactId>hibernate-types-52</artifactId>
 * 	<version>2.12.0</version>
 * </dependency>
 */
@Entity
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class)
})

public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    // one exam has one course, one course has many exams
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course")
    private Course course;

    private String title;
    private String description;
    private String location;
    private LocalDateTime publishTime;
    private LocalDateTime examTime;
    private int durationSeconds;

    @Enumerated(EnumType.ORDINAL)
    private ExamStatus examStatus;

    @OneToMany(mappedBy = "exam")
    private Set<StudentExamRelation> studentExamRelations = new HashSet<>();

    @Type(type = "json")
    @Column(name = "paper_content", columnDefinition = "json")
    private Map<String,Object> blankPaper = new HashMap<>();

    public enum ExamStatus {
        SETTING, REGISTERING, GRADING, FINISHED
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Exam() {
    }

    public Exam(Course course,
                String title,
                String description,
                String location,
                LocalDateTime publishTime,
                LocalDateTime examTime,
                int durationSeconds,
                ExamStatus examStatus) {
        this.course = course;
        this.title = title;
        this.description = description;
        this.location = location;
        this.publishTime = publishTime;
        this.examTime = examTime;
        this.durationSeconds = durationSeconds;
        this.examStatus = examStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    public LocalDateTime getExamTime() {
        return examTime;
    }

    public void setExamTime(LocalDateTime examTime) {
        this.examTime = examTime;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public ExamStatus getExamStatus() {
        return examStatus;
    }

    public void setExamStatus(ExamStatus examStatus) {
        this.examStatus = examStatus;
    }

    public Map<String, Object> getBlankPaper() {
        return blankPaper;
    }

    public void setBlankPaper(Map<String, Object> paperContent) {
        this.blankPaper = paperContent;
    }

    public Set<StudentExamRelation> getStudentExamRelations() {
        return studentExamRelations;
    }

    public void setStudentExamRelations(Set<StudentExamRelation> studentExamRelations) {
        this.studentExamRelations = studentExamRelations;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
