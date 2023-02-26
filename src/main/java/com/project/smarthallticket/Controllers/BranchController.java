package com.project.smarthallticket.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.smarthallticket.Entity.Branch;
import com.project.smarthallticket.Repositories.BranchRepository;

@RestController
public class BranchController {
	
	@Autowired 
	BranchRepository branchRepository;
	
	@GetMapping(value="/branches")
	public List<Branch> getAllBranches() {
		List<Branch> branches= (List<Branch>) branchRepository.findAll();
		return branches;

	}
	
	@PostMapping(value="/branches")
	public String addBranch(@RequestBody Branch b) {
		branchRepository.save(b);
		return "success";
		
	}
	
	

}
