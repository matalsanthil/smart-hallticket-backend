package com.project.smarthallticket.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.smarthallticket.Entity.Enroll;

@Repository(value = "enrollRepository")
public interface EnrollRepository extends CrudRepository<Enroll, Integer> {

	List<Enroll> findByExamIdAndStudentId(int examId, int studentId);

	List<Enroll> findByExamId(int examId);

}
