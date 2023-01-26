package com.project.smarthallticket.Controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.smarthallticket.Entity.Student;
import com.project.smarthallticket.Repositories.StudentRepository;

@RestController
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@GetMapping(value = "/students")
	public List<Student> getAllStudents() {
		
		List<Student> students = (List<Student>) studentRepository.findAll();
		
		return students;
	}
	
	@PostMapping(value = "/students")
	public String addStudent(@RequestBody Student s) {
		
		studentRepository.save(s);
		return "Success";

	}

}
