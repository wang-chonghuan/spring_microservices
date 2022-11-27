package com.wang.teachermsrv.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    public Course(String name, CourseStatus courseStatus, Teacher teacher) {
        this.name = name;
        this.courseStatus = courseStatus;
        this.teacher = teacher;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private CourseStatus courseStatus;
    public enum CourseStatus {
        ACTIVE, FINISHED
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="teacher")
    private Teacher teacher;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    @JsonIgnore
    private List<Exam> exams;

    /**
     * Caused by: org.hibernate.PersistentObjectException: detached entity passed to persist: com.wang.onlineexam.domain.Student
     * after add students here, when saving, will report this exception
     * it is bcz the cascade, should be set to CascadeType.MERGE !!!!!!
     * refer to https://www.baeldung.com/hibernate-detached-entity-passed-to-persist
     * "The cascade type is set to CascadeType.MERGE. Therefore, we'll only propagate merge operations to the associated Post."
     */
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "COURSE_STUDENT_RELATION",
            joinColumns = @JoinColumn(name = "COURSE_ID"),
            inverseJoinColumns = @JoinColumn(name = "STUDENT_ID")
    )
    private Set<Student> students = new HashSet<>();
}
