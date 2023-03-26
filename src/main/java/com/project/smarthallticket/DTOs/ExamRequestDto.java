package com.project.smarthallticket.DTOs;

public class ExamRequestDto {

	private String name;
	private String heldYear;
	private int branchId;
	
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
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	
	

}
