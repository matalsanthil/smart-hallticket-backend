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

import com.project.smarthallticket.Entity.Subject;
import com.project.smarthallticket.Repositories.SubjectRepository;

@CrossOrigin
@RestController
public class SubjectController {
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@GetMapping(value = "/getAllSubjects")
	public ResponseEntity<List<Subject>> getAllSubjects(){
		
		try {
			
			List<Subject> subjects = (List<Subject>) subjectRepository.findAllByOrderByCode();
			
			if(subjects.size() == 0) {
				throw new  Exception("No Records to show!");
			}
			
			return new ResponseEntity<List<Subject>>(subjects, HttpStatus.OK);
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
	}
	
	@PostMapping(value = "/AddSubject")
	public ResponseEntity<String> addSubject(@RequestBody Subject subject) {
		
		try {
			
			List<Subject> subjects = (List<Subject>) subjectRepository.findByCode(subject.getCode());
			
			if(subjects.size() > 0) {
				throw new  Exception("Subject Code Already Used !");
			}
			
			
			subjectRepository.save(subject);
			
			return new ResponseEntity<String>("Subject Added Successfuly !", HttpStatus.OK);
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
	}

}
