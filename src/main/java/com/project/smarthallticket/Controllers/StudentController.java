package com.project.smarthallticket.Controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.smarthallticket.DTOs.ExamDto;
import com.project.smarthallticket.DTOs.StudentCredDto;
import com.project.smarthallticket.DTOs.StudentDto;
import com.project.smarthallticket.DTOs.SubjectInfoDto;
import com.project.smarthallticket.Entity.Branch;
import com.project.smarthallticket.Entity.Exam;
import com.project.smarthallticket.Entity.Student;
import com.project.smarthallticket.Entity.Subject;
import com.project.smarthallticket.Repositories.BranchRepository;
import com.project.smarthallticket.Repositories.ExamSubjectRepository;
import com.project.smarthallticket.Repositories.StudentRepository;

@CrossOrigin
@RestController
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	BranchRepository branchRepository;
	
	@Autowired
	ExamSubjectRepository examSubjectRepository;
	
	@PostMapping(value = "/authenticateStudent/")
	public ResponseEntity<Student> getAllStudents(@RequestBody StudentCredDto studentCreds ) {
		
		  try { 
			 
			  System.out.println(studentCreds.getContactNo());
			  System.out.println(studentCreds.getPassword());
		  
			  List<Student> student = studentRepository.findByContactNo(studentCreds.getContactNo());
			  
			  if(student.size() == 0)
					throw new  Exception("Invalid Contact Number or Password !");
			  
			  if(!student.get(0).getPassword().equals(studentCreds.getPassword()))
				  throw new  Exception("Invalid Contact Number or Password !");
			  
			  student.get(0).setPassword("");
			  student.get(0).setExams(null);
			  return new ResponseEntity<Student>(student.get(0), HttpStatus.OK);
			  
		  
		  } catch (Exception e) { 
			  throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		  }
		 
	}
	
	@PostMapping(value = "/addStudent")
	public ResponseEntity<String> addStudent(@RequestBody StudentDto student) {
		
		try {
			
			student.setEmailId(student.getEmailId().toLowerCase());
			
			List<Student> existingStudents = studentRepository.findByEmailId(student.getEmailId());
			
			if(existingStudents.size() > 0)
				throw new  Exception("Email Id Already Used !");
			
			existingStudents = studentRepository.findByContactNo(student.getContactNo());
			
			if(existingStudents.size() > 0)
				throw new  Exception("Mobile Number Already Used !");
			
			existingStudents = studentRepository.findByAadharNumber(student.getAadharNumber());
			
			if(existingStudents.size() > 0)
				throw new  Exception("Aadhar Number Already Used !");
			
			Optional<Branch> optionalBranch = branchRepository.findById(student.getBranchId());
			Branch branch = optionalBranch.orElseThrow(() -> new Exception("Invalid Branch ID !"));
			
			Student studentEntity = StudentDto.dtoToEntity(student);
			studentEntity.setBranch(branch);
			
			studentRepository.save(studentEntity);	
			return new ResponseEntity<String>("Student added successfully !", HttpStatus.OK);
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FOUND, e.getMessage());
		}

	}
	
	@GetMapping("studentExam/{studentId}")
	public ResponseEntity<List<ExamDto>> getExams(@PathVariable int studentId) {
		
		try {
			
			Optional<Student> optionalStudent = studentRepository.findById(studentId);
			Student student = optionalStudent.orElseThrow(() -> new Exception("Invalid Student ID !"));
			
			List<ExamDto> examResponse = new ArrayList<>();

			for (Exam exam : student.getExams()) {
				
				ExamDto examDto = new ExamDto();
				examDto.setId(exam.getId());
				examDto.setName(exam.getName());
				examDto.setHeldYear(exam.getHeldYear());
				examDto.setBranchName(exam.getBranch().getName());

				Set<SubjectInfoDto> subjectInfoDtos = new HashSet<>();

				for (Subject subject : exam.getSubjects()) {
					SubjectInfoDto subjectInfoDto = new SubjectInfoDto();
					subjectInfoDto.setCode(subject.getCode());
					subjectInfoDto.setName(subject.getName());
					subjectInfoDto.setId(subject.getId());
					subjectInfoDto.setDateOfExam(examSubjectRepository
							.findByExamIdAndSubjectId(exam.getId(), subject.getId()).get(0).getDateOfExam());

					subjectInfoDtos.add(subjectInfoDto);
				}

				examDto.setSubjects(subjectInfoDtos);
				examResponse.add(examDto);
			}
			
			return new ResponseEntity<List<ExamDto>>(examResponse, HttpStatus.OK);
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
	}
	
	@GetMapping("student/{studentId}")
	public ResponseEntity<Student> getStudent(@PathVariable int studentId) {
		
		try {
			
			Optional<Student> optionalStudent = studentRepository.findById(studentId);
			Student student = optionalStudent.orElseThrow(() -> new Exception("Invalid Student ID !"));
			
			student.setPassword("");
			student.setExams(Collections.emptySet());
			
			return new ResponseEntity<Student>(student, HttpStatus.OK);
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
