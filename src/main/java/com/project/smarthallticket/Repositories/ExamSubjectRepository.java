package com.project.smarthallticket.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.project.smarthallticket.Entity.ExamSubject;

@Repository(value = "examSubjectRepository")
public interface ExamSubjectRepository extends CrudRepository<ExamSubject, Integer> {

	List<ExamSubject> findByExamIdAndSubjectId(int id, int id2);

	List<ExamSubject> findByExamId(int examId);

}
