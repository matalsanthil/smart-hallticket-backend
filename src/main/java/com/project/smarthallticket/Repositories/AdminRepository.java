package com.project.smarthallticket.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.smarthallticket.Entity.Admin;

@Repository(value = "adminRepository")
public interface AdminRepository extends CrudRepository<Admin, Integer> {

}
