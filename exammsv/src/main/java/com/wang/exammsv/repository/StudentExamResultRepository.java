package com.wang.exammsv.repository;

import com.wang.exammsv.domain.StudentExamResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentExamResultRepository extends CrudRepository<StudentExamResult, Long> {
    List<StudentExamResult> findByExamId(long examId);

    List<StudentExamResult> findByStudentIdAndExamId(@Param("student_id")long studentId, @Param("exam_id")long examId);

    default void updateAnsweredPaper(StudentExamResult result) {
        var oldResult = findByStudentIdAndExamId(
                result.getStudentId(), result.getExamId()).stream().findFirst();
        // bug if a student resubmit his paper, the state should not be changed to submit, bcz other students may be all fullygraded
        if(oldResult.isPresent()) {
            oldResult.get().setAnsweredpaper(result.getAnsweredpaper());
            oldResult.get().setGradeState(result.getGradeState());
            save(oldResult.get());
        } else {
            save(result);
        }
    }
}
