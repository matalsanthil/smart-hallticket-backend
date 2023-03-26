package com.project.smarthallticket.Controllers;

import java.util.ArrayList;
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
import com.project.smarthallticket.DTOs.ExamRequestDto;
import com.project.smarthallticket.DTOs.StudentInfoDto;
import com.project.smarthallticket.DTOs.SubjectInfoDto;
import com.project.smarthallticket.Entity.Branch;
import com.project.smarthallticket.Entity.Enroll;
import com.project.smarthallticket.Entity.Exam;
import com.project.smarthallticket.Entity.ExamSubject;
import com.project.smarthallticket.Entity.Student;
import com.project.smarthallticket.Entity.Subject;
import com.project.smarthallticket.Repositories.BranchRepository;
import com.project.smarthallticket.Repositories.EnrollRepository;
import com.project.smarthallticket.Repositories.ExamRepository;
import com.project.smarthallticket.Repositories.ExamSubjectRepository;

@CrossOrigin
@RestController
public class ExamController {

	@Autowired
	ExamRepository examRepository;

	@Autowired
	ExamSubjectRepository examSubjectRepository;

	@Autowired
	BranchRepository branchRepository;
	
	@Autowired
	EnrollRepository enrollRepository;

	@GetMapping(value = "/exams")
	public ResponseEntity<List<ExamDto>> getAllExams() {

		try {

			List<Exam> exams = (List<Exam>) examRepository.findAll();

			if (exams.size() == 0) {
				throw new Exception("No Records to show!");
			}

			List<ExamDto> examResponse = new ArrayList<>();

			for (Exam exam : exams) {
				ExamDto examDto = new ExamDto();
				examDto.setId(exam.getId());
				examDto.setName(exam.getName());
				examDto.setHeldYear(exam.getHeldYear());
				examDto.setBranchName(exam.getBranch().getName());

				Set<SubjectInfoDto> subjectInfoDtos = new HashSet<>();
				Set<StudentInfoDto> studentInfoDtos = new HashSet<>();

				for (Subject subject : exam.getSubjects()) {
					SubjectInfoDto subjectInfoDto = new SubjectInfoDto();
					subjectInfoDto.setCode(subject.getCode());
					subjectInfoDto.setName(subject.getName());
					subjectInfoDto.setId(subject.getId());
					subjectInfoDto.setDateOfExam(examSubjectRepository
							.findByExamIdAndSubjectId(exam.getId(), subject.getId()).get(0).getDateOfExam());

					subjectInfoDtos.add(subjectInfoDto);
				}
				
				for(Student student: exam.getStudents()) {
					studentInfoDtos.add(StudentInfoDto.EntityToDto(student));
				}

				examDto.setSubjects(subjectInfoDtos);
				examDto.setStudents(studentInfoDtos);
				
				examResponse.add(examDto);
			}

			return new ResponseEntity<List<ExamDto>>(examResponse, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	@GetMapping(value = "/exams/{branchId}")
	public ResponseEntity<List<ExamDto>> getExamsByBranch(@PathVariable int branchId) {

		try {

			Optional<Branch> optionalBranch = branchRepository.findById(branchId);

			Branch branch = optionalBranch.orElseThrow(() -> new Exception("Invalid Branch !"));

			List<Exam> exams = (List<Exam>) examRepository.findByBranch(branch);

			if (exams.size() == 0) {
				throw new Exception("No Records to show!");
			}

			List<ExamDto> examResponse = new ArrayList<>();

			for (Exam exam : exams) {
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

	@PostMapping(value = "/exams/subject")
	public ResponseEntity<String> addSubject(@RequestBody ExamSubject examSub) {

		examSubjectRepository.save(examSub);

		return new ResponseEntity<String>("Subject Added Successfully !", HttpStatus.OK);
	}

	@PostMapping(value = "/exams")
	public ResponseEntity<String> addExam(@RequestBody ExamRequestDto examDto) {

		try {

			List<Exam> existingExams = examRepository.findByNameAndHeldYear(examDto.getName(), examDto.getHeldYear());

			for (Exam existingExam : existingExams) {
				if (existingExam.getBranch().getId() == examDto.getBranchId())
					throw new Exception("Exam Already Exists for given year and branch !");
			}

			Optional<Branch> optionalBranch = branchRepository.findById(examDto.getBranchId());

			Branch branch = optionalBranch.orElseThrow(() -> new Exception("Invalid Branch ID !"));

			Exam newExam = new Exam();
			newExam.setName(examDto.getName());
			newExam.setHeldYear(examDto.getHeldYear());
			newExam.setBranch(branch);

			examRepository.save(newExam);

			return new ResponseEntity<String>("Exam Added Successfully !", HttpStatus.OK);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}
	
	@PostMapping(value = "/exams/enroll")
	public ResponseEntity<String> enrollExam(@RequestBody Enroll enroll) {
		
		try {
			
			List<Enroll> existingEnroll = enrollRepository.findByExamIdAndStudentId(enroll.getExamId(), enroll.getStudentId());
			
			if(existingEnroll.size() > 0) {
				throw new Exception("Already Enroll for this exam! You can check this in enrolled Exam Tab");
			}
			
			enrollRepository.save(enroll);
			
			return new ResponseEntity<String>("Enrolled to the exam !", HttpStatus.OK);
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}	
		
	}
	
	
}
