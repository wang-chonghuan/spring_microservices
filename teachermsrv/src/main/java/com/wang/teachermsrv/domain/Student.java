package com.wang.teachermsrv.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

// todo! 此处如果用@Data注解，因为会自动生成hashCode()方法，会导致无法生成Set<Student> 所以要用@getter @setter,重写hashCode方法
// failed to lazily initialize a collection of role: com.wang.teachermsrv.domain.Student.courses, could not initialize proxy - no Session
// ......
// at org.hibernate.collection.internal.PersistentSet.hashCode(PersistentSet.java:458) ~[hibernate-core-5.6.14.Final.jar:5.6.14.Final]
// at com.wang.teachermsrv.domain.Student.hashCode(Student.java:11) ~[classes/:na]
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    private String name;
    private String email;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses = new HashSet<>();

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
