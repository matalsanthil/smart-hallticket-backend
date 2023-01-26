package com.project.smarthallticket.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.smarthallticket.Entity.Student;

@Repository(value = "studentRepository")
public interface StudentRepository extends CrudRepository<Student, Integer>{

}
