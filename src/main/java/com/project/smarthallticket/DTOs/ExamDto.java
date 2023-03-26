package com.project.smarthallticket.DTOs;

import java.util.Set;

public class ExamDto {
	private int id;
	private String name;
	private String heldYear;
	private Set<SubjectInfoDto> subjects;
	private Set<StudentInfoDto> students;
	private String branchName;
	
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeldYear() {
		return heldYear;
	}
	public void setHeldYear(String heldYear) {
		this.heldYear = heldYear;
	}
	public Set<SubjectInfoDto> getSubjects() {
		return subjects;
	}
	public void setSubjects(Set<SubjectInfoDto> subjects) {
		this.subjects = subjects;
	}
	public Set<StudentInfoDto> getStudents() {
		return students;
	}
	public void setStudents(Set<StudentInfoDto> students) {
		this.students = students;
	}
	
	
}
