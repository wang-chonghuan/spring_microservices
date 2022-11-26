package com.wang.onlineexam.domain;

import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// for many-to-many relation with this intermediate entity,
// you should only add a record to this table
// you should not add StudentExamRelation obj to neither Student nor Exam entity!
// todo //@org.hibernate.annotations.Immutable if use this annotation, the record and not be updated!!!
@Entity
@Table(name = "student_exam_relation")
//@org.hibernate.annotations.Immutable
public class StudentExamRelation {

    private double score;

    @Type(type = "json")
    @Column(name = "paper_answered", columnDefinition = "json")
    private Map<String,Object> paperAnswered = new HashMap<>();

    @Embeddable
    public static class Id implements Serializable {
        @Column(name = "STUDENT_ID")
        private Long studentId;
        @Column(name = "EXAM_ID")
        private Long examId;

        public Id() {
        }

        public Id(Long studentId, Long itemId) {
            this.studentId = studentId;
            this.examId = examId;
        }
        //implementing equals and hashCode
    }

    @EmbeddedId
    private Id id = new Id();
/*
    @Column(updatable = false)
    @NotNull
    @CreationTimestamp
    private LocalDateTime addedOn;
*/
    @ManyToOne
    @JoinColumn(
            name = "STUDENT_ID",
            insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(
            name = "EXAM_ID",
            insertable = false, updatable = false)
    private Exam exam;

    public StudentExamRelation(Student student, Exam exam) {
        this.student = student;
        this.exam = exam;
        this.id.studentId = student.getId();
        this.id.examId = exam.getId();
        this.score = 0.0;
        // TODO!!! should add student to the set-student in obj-exam? should add exam to the set-exam in obj-student? the db is ok if not add
        // result: the db is not ok if add
        // throws: Caused by: org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: com.wang.onlineexam.domain.Student.studentExamRelations, could not initialize proxy - no Session
        // student.getStudentExamRelations().add(this);
        // exam.getStudentExamRelations().add(this);
        //category.addCategorizedItem(this);
        //item.addCategorizedItem(this);
    }

    public StudentExamRelation() {

    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Map<String, Object> getPaperAnswered() {
        return paperAnswered;
    }

    public void setPaperAnswered(Map<String, Object> paperAnswered) {
        this.paperAnswered = paperAnswered;
    }
}

/**
 public class DemoManyToMany {

@Entity
public class Blog {
@Id
@Column(name="id")
private int id;

@ManyToMany(cascade= CascadeType.ALL)
@JoinTable(
name = "blog_tag_relation",
joinColumns = @JoinColumn(name = "blog_id", referencedColumnName = "id"),
inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
private List<Tag> tags = new ArrayList<Tag>();
}

@Entity
public class Tag {
@Id
@Column(name="id")
private int id;
@ManyToMany(mappedBy="BlogTagRelation")
private List<Blog> blogs = new ArrayList<Blog>();
}

@Entity
public class BlogTagRelation {
@Id
@Column(name="id")
private int id;

@Column(name="blog_id")
private int blogId;

@Column(name="tag_id")
private int tagId;
}
}
 */
