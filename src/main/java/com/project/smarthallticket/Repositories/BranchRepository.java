package com.project.smarthallticket.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.smarthallticket.Entity.Branch;

@Repository(value = "branchRepository")
public interface BranchRepository extends CrudRepository<Branch,Integer>{

	List<Branch> findByNameIgnoreCase(String name);


}
