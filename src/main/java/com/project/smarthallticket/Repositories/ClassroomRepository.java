package com.project.smarthallticket.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.smarthallticket.Entity.Classroom;

@Repository (value="classroomRepository")
public interface ClassroomRepository extends CrudRepository<Classroom,Integer> {
	
	
	@Query(
			  value = "SELECT * FROM Classroom Order by wing, floor, number", 
			  nativeQuery = true)
	List<Classroom> findAllByOrderByWingFloorNumberAsc();

	List<Classroom> findByWingAndFloorAndNumber(String wing, int floor, int number);
	

}
