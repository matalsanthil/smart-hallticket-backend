package com.project.smarthallticket.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.smarthallticket.Entity.Classroom;
import com.project.smarthallticket.Repositories.ClassroomRepository;

@CrossOrigin
@RestController
public class ClassroomController {
	
	@Autowired
	ClassroomRepository classroomRepository;
	
	@GetMapping(value="/getAllClassrooms")
	public ResponseEntity<List<Classroom>> getAllClassrooms() {
		
		try {
			
			List<Classroom> classrooms= (List<Classroom>) classroomRepository.findAllByOrderByWingFloorNumberAsc();
			
			if(classrooms.size() == 0) {
				throw new  Exception("No Records to show!");
			}
			
			return new ResponseEntity<List<Classroom>>(classrooms, HttpStatus.OK);
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.OK, e.getMessage());
		}
		

	}
	
	@PostMapping(value="/addClassroom")
	public ResponseEntity<String> addClassroom(@RequestBody Classroom classroom) {
		try {
			
			List<Classroom> classrooms = classroomRepository.findByWingAndFloorAndNumber(
													classroom.getWing(), classroom.getFloor(), classroom.getNumber());
			
			if(classrooms.size() > 0) {
				throw new  Exception("Classroom Already Exists!");
			}
			
			classroomRepository.save(classroom);
			
			return new ResponseEntity<String>("Classroom added successfully !", HttpStatus.OK);
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FOUND, e.getMessage());
		}
	}

}
