package com.wang.mgtmsv.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public enum ExamStatus {
        SETTING, REGISTERING, GRADING, FINISHED
    }
}
