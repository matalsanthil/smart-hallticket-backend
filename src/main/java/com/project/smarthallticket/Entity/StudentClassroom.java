package com.project.smarthallticket.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student_classroom")
public class StudentClassroom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "classroom_id")
	private int classroomId;
	
	@Column(name = "student_id")
	private int studentId;
	
	@Column(name = "exam_subject_id")
	private int examSubjectId;
	
	private Date dateOfExam;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(int classroomId) {
		this.classroomId = classroomId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getExamSubjectId() {
		return examSubjectId;
	}

	public void setExamSubjectId(int examSubjectId) {
		this.examSubjectId = examSubjectId;
	}

	public Date getDateOfExam() {
		return dateOfExam;
	}

	public void setDateOfExam(Date dateOfExam) {
		this.dateOfExam = dateOfExam;
	}
	
	
	
}
