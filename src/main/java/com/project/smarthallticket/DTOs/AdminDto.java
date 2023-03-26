package com.project.smarthallticket.DTOs;

import com.project.smarthallticket.Entity.Admin;

public class AdminDto {
	private int id;
	private String emailId;
	private String password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public static Admin dtoToEntity(AdminDto adminDto) {
		Admin adminEntity = new Admin();
		
		adminEntity.setEmailId(adminDto.getEmailId());
		adminEntity.setPassword(adminDto.getPassword());
		
		return adminEntity;
	}
	
	public static AdminDto entityToDto(Admin admin) {
		AdminDto adminDto = new AdminDto();
		adminDto.setId(admin.getId());
		adminDto.setEmailId(admin.getEmailId());
		return adminDto;
	}
}
