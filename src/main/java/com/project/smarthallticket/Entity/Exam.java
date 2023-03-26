package com.project.smarthallticket.Entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "exams")
public class Exam {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String heldYear;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
		name = "exam_subject",
		joinColumns = @JoinColumn(
			name = "exam_id", referencedColumnName = "id"
		),
		inverseJoinColumns = @JoinColumn(
			name = "subject_id", referencedColumnName = "id"
		)
	)
	private Set<Subject> subjects;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
		name = "enroll",
		joinColumns = @JoinColumn(
			name = "exam_id", referencedColumnName = "id"
		),
		inverseJoinColumns = @JoinColumn(
			name = "student_id", referencedColumnName = "id"
		)
	)
	private Set<Student> students;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
	private Branch branch;
	
	
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}
	public String getHeldYear() {
		return heldYear;
	}
	public void setHeldYear(String heldYear) {
		this.heldYear = heldYear;
	}
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	

}
