package com.wang.onlineexam.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Course {
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    @JsonIgnore
    private List<Exam> exams;

    public Course() {
    }

    public Course(String name, CourseStatus courseStatus, Teacher teacher) {
        this.name = name;
        this.courseStatus = courseStatus;
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public CourseStatus getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
