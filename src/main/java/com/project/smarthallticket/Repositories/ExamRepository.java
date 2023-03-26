package com.project.smarthallticket.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.smarthallticket.Entity.Branch;
import com.project.smarthallticket.Entity.Exam;

@Repository (value = "examrepository")
public interface ExamRepository extends CrudRepository<Exam, Integer>{

	List<Exam> findByNameAndHeldYear(String name, String heldYear);

	List<Exam> findByBranch(Branch branch);

}
