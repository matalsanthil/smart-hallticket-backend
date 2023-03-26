package com.project.smarthallticket.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.smarthallticket.Entity.Student;

@Repository(value = "studentRepository")
public interface StudentRepository extends CrudRepository<Student, Integer>{

	List<Student> findByEmailId(String emailId);

	List<Student> findByContactNo(long contactNo);

	List<Student> findByAadharNumber(long aadharNumber);

}
