package com.management.studentmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.management.studentmanagement.entity.Course;

import jakarta.transaction.Transactional;

public interface CourseRepository extends JpaRepository<Course, Integer>{
	
	Optional<Course> findByCourseName(String courseName);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM _course c WHERE c.course_name = :p", nativeQuery = true)
	void deleteByCourseName(@Param("p") String p); 

}
