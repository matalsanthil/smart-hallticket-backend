package com.project.smarthallticket.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.smarthallticket.Entity.Admin;
import com.project.smarthallticket.Repositories.AdminRepository;


@RestController
public class AdminController {
	
	@Autowired 
	AdminRepository adminRepository;
	
	@GetMapping(value="/admins")
	public List<Admin> getAllAdmins() {
		
		List<Admin> admins= (List<Admin>) adminRepository.findAll();
		return admins;

	}
	
	@PostMapping(value="/admins")
	public String addAdmin(@RequestBody Admin a) {
		
		adminRepository.save(a);
		return "success";
		
	}
	
}
