package com.project.smarthallticket.DTOs;

import java.util.Date;

import com.project.smarthallticket.Entity.Student;

public class StudentDto {
	
	private int id;
	private String fname;
	private String lname;
	private long aadharNumber;
	private Date dob;
	private long contactNo;
	private String emailId;
	private String address;
	private String password;
	private int branchId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	
	public static Student dtoToEntity(StudentDto studentDto) {
		
		Student student = new Student();
		
		student.setAadharNumber(studentDto.getAadharNumber());
		student.setAddress(studentDto.getAddress());
		student.setContactNo(studentDto.getContactNo());
		student.setDob(studentDto.getDob());
		student.setEmailId(studentDto.getEmailId());
		student.setFname(studentDto.getFname());
		student.setLname(studentDto.getLname());
		student.setPassword(studentDto.getPassword());
		
		return student;
	}
	

}
