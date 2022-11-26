package com.wang.onlineexam;

import com.wang.onlineexam.domain.Teacher;
import com.wang.onlineexam.domain.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * here, cant use @DataJpaTest, otherwise, will throw an exception that cant find DataInitializaion bean!!!
 * must use @SpringBootTest instead
 * refer to https://stackoverflow.com/questions/44925324/spring-boot-test-no-qualifying-bean-of-type-available
 *
 *  java.lang.IllegalStateException: Failed to load ApplicationContext
 * Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'onlineExamApplication': Unsatisfied dependency expressed through field 'dataInitialization'; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.wang.onlineexam.DataInitialization' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
 * Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.wang.onlineexam.DataInitialization' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
 */
@SpringBootTest
public class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    void saveTeacher() {
        Teacher teacher3 = new Teacher("teacher3.name", "teacher3.email");
        Teacher retTeacher3 = teacherRepository.save(teacher3);
        assertThat(teacherRepository.findById(retTeacher3.getId()).isPresent()).isTrue();
    }
}
