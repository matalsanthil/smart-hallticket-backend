package com.project.smarthallticket.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.smarthallticket.Entity.Subject;
import com.project.smarthallticket.Repositories.SubjectRepository;

@RestController
public class SubjectController {
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@GetMapping(value = "/subjects")
	public List<Subject> getAllSubjects(){
		
		List<Subject> subjects = (List<Subject>) subjectRepository.findAll();
		return subjects;
		
	}
	
	@PostMapping(value = "/subjects")
	public String addSubject(@RequestBody Subject s) {
		
		subjectRepository.save(s);
		return "success";

		
	}

}
