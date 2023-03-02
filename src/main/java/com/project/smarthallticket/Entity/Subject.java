package com.project.smarthallticket.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Subjects")
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int code;
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getCode() {
		return code;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	
	

}
