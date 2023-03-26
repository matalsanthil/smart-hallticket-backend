package com.project.smarthallticket.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.smarthallticket.Entity.Branch;
import com.project.smarthallticket.Repositories.BranchRepository;

@CrossOrigin
@RestController
public class BranchController {
	
	@Autowired 
	BranchRepository branchRepository;
	
	@GetMapping(value="/branches")
	public ResponseEntity<List<Branch>> getAllBranches() {
		
		try {
			List<Branch> branches= (List<Branch>) branchRepository.findAll();
			
			if(branches.size() == 0) {
				throw new  Exception("No Records to show!");
			}
			
			return new ResponseEntity<List<Branch>>(branches, HttpStatus.OK);
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.OK, e.getMessage());
		}
		

	}
	
	@PostMapping(value="/branches")
	public ResponseEntity<String> addBranch(@RequestBody Branch branch) {
		try {
			
			List<Branch> branches= (List<Branch>) branchRepository.findByNameIgnoreCase(branch.getName());
			if(branches.size() > 0) {
				throw new  Exception("Branch Already Exists !");
			}
			
			branchRepository.save(branch);
			
			return new ResponseEntity<String>("Branch added successfully !", HttpStatus.OK);
			
		} catch (Exception e) {

			throw new ResponseStatusException(HttpStatus.FOUND, e.getMessage());
		}
		
	}
	
	

}
