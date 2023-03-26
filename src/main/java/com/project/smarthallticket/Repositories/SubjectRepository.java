package com.project.smarthallticket.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.smarthallticket.Entity.Subject;

@Repository(value = "subjectRepository")
public interface SubjectRepository extends CrudRepository<Subject,Integer>{

	List<Subject> findByCode(String code);

	List<Subject> findByNameIgnoreCase(String name);

	List<Subject> findAllByOrderByCode();

}
