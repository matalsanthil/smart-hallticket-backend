package com.project.smarthallticket.Controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import com.project.smarthallticket.DTOs.AdminDto;
import com.project.smarthallticket.DTOs.GetClassRoomResponseDto;
import com.project.smarthallticket.Entity.Admin;
import com.project.smarthallticket.Entity.Classroom;
import com.project.smarthallticket.Entity.Enroll;
import com.project.smarthallticket.Entity.ExamSubject;
import com.project.smarthallticket.Entity.Student;
import com.project.smarthallticket.Entity.StudentClassroom;
import com.project.smarthallticket.Entity.Subject;
import com.project.smarthallticket.Repositories.AdminRepository;
import com.project.smarthallticket.Repositories.BranchRepository;
import com.project.smarthallticket.Repositories.ClassroomRepository;
import com.project.smarthallticket.Repositories.EnrollRepository;
import com.project.smarthallticket.Repositories.ExamRepository;
import com.project.smarthallticket.Repositories.ExamSubjectRepository;
import com.project.smarthallticket.Repositories.StudentClassroomRepository;
import com.project.smarthallticket.Repositories.StudentRepository;
import com.project.smarthallticket.Repositories.SubjectRepository;

@CrossOrigin
@RestController
public class AdminController {

	@Autowired
	ExamRepository examRepository;

	@Autowired
	ExamSubjectRepository examSubjectRepository;

	@Autowired
	BranchRepository branchRepository;

	@Autowired
	EnrollRepository enrollRepository;

	@Autowired
	ClassroomRepository classroomRepository;

	@Autowired
	StudentClassroomRepository studentClassroomRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	SubjectRepository subjectRepository;

	@Autowired
	AdminRepository adminRepository;

	@PostMapping(value = "/authenticateAdmin")
	public ResponseEntity<AdminDto> authenticateAdmin(@RequestBody AdminDto adminDto) {

		try {
			System.out.println(adminDto.getEmailId());
			List<Admin> adminList = adminRepository.findByEmailId(adminDto.getEmailId());

			if (adminList.isEmpty()) {
				throw new Exception("Invalid Email Id or password");
			}

			String passwordFromDb = adminList.get(0).getPassword();
			System.out.println(passwordFromDb);

			if (!passwordFromDb.equals(adminDto.getPassword())) {
				throw new Exception("Invalid Email Id or password");
			}

			AdminDto result = AdminDto.entityToDto(adminList.get(0));
			return new ResponseEntity<AdminDto>(result, HttpStatus.OK);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}

	@PostMapping(value = "/registerAdmin")
	public ResponseEntity<String> addAdmin(@RequestBody AdminDto adminDto) {

		try {
			System.out.println(adminDto.getEmailId());
			List<Admin> adminList = adminRepository.findByEmailId(adminDto.getEmailId());

			if (!adminList.isEmpty()) {
				throw new Exception("Email Id already taken, Please try new email id !");
			}

			Admin admin = AdminDto.dtoToEntity(adminDto);
			adminRepository.save(admin);

			return new ResponseEntity<String>("Admin added successfully!", HttpStatus.OK);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}

	}

	@GetMapping(value = "getClassroom/{aadharNumber}")
	public ResponseEntity<GetClassRoomResponseDto> getClassroom(@PathVariable long aadharNumber) {
		try {

			List<Student> students = studentRepository.findByAadharNumber(aadharNumber);

			if (students.size() == 0) {
				throw new Exception("Invalid Aadhar Number ! Please check your details");
			}

			List<StudentClassroom> classrooms = studentClassroomRepository
					.findByStudentIdAndCurrentDate(students.get(0).getId());

			if (classrooms.size() == 0) {
				throw new Exception("Classroom is not allocated yet or you may not have any exam today !");
			}

			Optional<Classroom> optionalClassroom = classroomRepository.findById(classrooms.get(0).getClassroomId());
			Classroom classroom = optionalClassroom.orElseThrow(() -> new Exception("Something Went Wrong !"));
			
			Optional<ExamSubject> optionalExamSub = examSubjectRepository.findById(classrooms.get(0).getExamSubjectId());
			ExamSubject examSub = optionalExamSub.orElseThrow(() -> new Exception("Something Went Wrong !"));
			
			Optional<Subject> optionalSub = subjectRepository.findById(examSub.getSubjectId());
			Subject sub = optionalSub.orElseThrow(() -> new Exception("Something Went Wrong !"));

			GetClassRoomResponseDto response = new GetClassRoomResponseDto();

			response.setAadharNumber(students.get(0).getAadharNumber());
			response.setContactNo(students.get(0).getContactNo());
			response.setDob(students.get(0).getDob());
			response.setBranchName(students.get(0).getBranch().getName());
			response.setEmailId(students.get(0).getEmailId());
			response.setFname(students.get(0).getFname());
			response.setLname(students.get(0).getLname());
			response.setDateOfExam(classrooms.get(0).getDateOfExam());

			response.setClassroom(classroom.getWing() + "-" + classroom.getFloor() + "0" + classroom.getNumber());
			response.setSeatNumber(classrooms.get(0).getSeatNumber());
			response.setSubjectName(sub.getName());

			return new ResponseEntity<GetClassRoomResponseDto>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping(value = "allocateClassrooms/{examId}")
	public ResponseEntity<String> allocateClassroom(@PathVariable int examId) {
		try {

			List<ExamSubject> examSubjects = examSubjectRepository.findByExamId(examId);
			List<Enroll> students = enrollRepository.findByExamId(examId);
			List<Classroom> classRooms = classroomRepository.findAllByOrderByWingFloorNumberAsc();

			int subjectNumber = 0;
			while (subjectNumber < examSubjects.size()) {
				int studentNumber = 0;
				int classroomNumber = 0;
				int numberOfStudents = 0;
				while (studentNumber < students.size()) {

					ExamSubject subject = examSubjects.get(subjectNumber);
					int student = students.get(studentNumber).getStudentId();

					List<StudentClassroom> existingStudentClassroom = studentClassroomRepository
							.findByStudentIdAndExamSubjectId(student, subject.getId());

					
					if (existingStudentClassroom.size() > 0) { studentNumber++; continue; }
					

					if (classroomNumber >= classRooms.size()) {
						throw new Exception("No Classroom Available ! Please add new classrooms..");
					}

					Classroom classroom = classRooms.get(classroomNumber);
					existingStudentClassroom = studentClassroomRepository.findByClassroomId(classroom.getId());
					
					int flag = 0;
					for (StudentClassroom studentClassroom : existingStudentClassroom) {
						if(subject.getId() != studentClassroom.getExamSubjectId()) {
							SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
							Date d1 = sdformat.parse(studentClassroom.getDateOfExam().toString());
							Date d2 = sdformat.parse(subject.getDateOfExam().toString());
							System.out.println(studentClassroom.getDateOfExam() + " " + subject.getDateOfExam() + " - "
									+ d1.compareTo(d2));
							if(d1.compareTo(d2) == 0) {
								flag = 1;
								break;
							}
						}
					}

					if (flag == 1 || classroom.getCapacity() == numberOfStudents) {
						classroomNumber++;
						continue;
					}

					// check classroom is available or not using date of exam

					StudentClassroom studentClassroom = new StudentClassroom();
					studentClassroom.setClassroomId(classroom.getId());
					studentClassroom.setExamSubjectId(subject.getId());
					studentClassroom.setStudentId(student);
					studentClassroom.setDateOfExam(examSubjects.get(subjectNumber).getDateOfExam());
					studentClassroom.setSeatNumber(numberOfStudents + 1);
					studentClassroomRepository.save(studentClassroom);

					studentNumber++;
					numberOfStudents++;

				}
				subjectNumber++;
			}

			return new ResponseEntity<String>("Classroom Allocated successfully!", HttpStatus.OK);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
