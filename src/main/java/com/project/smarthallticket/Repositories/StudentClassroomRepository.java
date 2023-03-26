package com.project.smarthallticket.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.smarthallticket.Entity.StudentClassroom;

@Repository(value = "studentClassroomRepository")
public interface StudentClassroomRepository extends CrudRepository<StudentClassroom, Integer> {

	List<StudentClassroom> findByStudentIdAndExamSubjectId(int studentId, int examSubjectId);

	List<StudentClassroom> findByClassroomIdAndExamSubjectId(int classroomId, int examSubjectId);
	
	@Query(
			  value = "SELECT *\r\n"
			  		+ "FROM student_classroom \r\n"
			  		+ "WHERE student_id = :studentId and DATE(date_of_exam) = CURDATE()+1;", 
			  nativeQuery = true)
	List<StudentClassroom> findByStudentIdAndCurrentDate(@Param("studentId") int studentId);

}
