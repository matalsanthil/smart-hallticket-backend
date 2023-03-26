package com.project.smarthallticket.DTOs;

import java.util.Date;

import com.project.smarthallticket.Entity.Student;

public class StudentInfoDto {

	private String fname;
	private String lname;
	private long aadharNumber;
	private Date dob;
	private long contactNo;
	private String emailId;
	private String address;
	private String branch;
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public long getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(long aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public long getContactNo() {
		return contactNo;
	}
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	public static StudentInfoDto EntityToDto(Student studentEntity) {
		
		StudentInfoDto student = new StudentInfoDto();
		
		student.setAadharNumber(studentEntity.getAadharNumber());
		student.setAddress(studentEntity.getAddress());
		student.setContactNo(studentEntity.getContactNo());
		student.setDob(studentEntity.getDob());
		student.setEmailId(studentEntity.getEmailId());
		student.setFname(studentEntity.getFname());
		student.setLname(studentEntity.getLname());
		student.setBranch(studentEntity.getBranch().getName());
		
		return student;
	}
	
}
