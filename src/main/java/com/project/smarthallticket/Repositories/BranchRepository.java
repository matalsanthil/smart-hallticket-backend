package com.project.smarthallticket.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.smarthallticket.Entity.Branch;

@Repository(value = "branchRepository")
public interface BranchRepository extends CrudRepository<Branch,Integer>{


}
