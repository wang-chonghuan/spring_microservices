package com.wang.onlineexam.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "student")
    private Set<StudentExamRelation> studentExamRelations = new HashSet<>();

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student() {
    }

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<StudentExamRelation> getStudentExamRelations() {
        return studentExamRelations;
    }

    public void setStudentExamRelations(Set<StudentExamRelation> studentExamRelations) {
        this.studentExamRelations = studentExamRelations;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
