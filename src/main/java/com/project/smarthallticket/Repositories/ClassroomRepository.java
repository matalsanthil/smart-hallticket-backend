package com.project.smarthallticket.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.smarthallticket.Entity.Classroom;

@Repository (value="classroomRepository")
public interface ClassroomRepository extends CrudRepository<Classroom,Integer> {
	

}
