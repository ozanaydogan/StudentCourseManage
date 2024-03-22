package com.management.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.studentmanagement.dto.AllCoursesResponse;
import com.management.studentmanagement.dto.CreateCourseRequest;
import com.management.studentmanagement.dto.CreateCourseResponse;
import com.management.studentmanagement.dto.DeleteCourseByIdResponse;
import com.management.studentmanagement.dto.DeleteCourseByNameResponse;
import com.management.studentmanagement.dto.FilterCoursebyStudentResponse;
import com.management.studentmanagement.service.CourseService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/course") 
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
    @PostMapping("/createCourse")
    public ResponseEntity<CreateCourseResponse> createCourse(@RequestBody CreateCourseRequest request) {
    	
        return ResponseEntity.ok(courseService.createCourse(request));
    }
    
    @DeleteMapping("/deleteCoursebyId/{id}")
    public ResponseEntity<DeleteCourseByIdResponse> deleteCoursebyId(@PathVariable Integer id) {
    	
    	return ResponseEntity.ok(courseService.deleteCoursebyId(id));
    }
    
    @DeleteMapping("/deleteCoursebyName/{name}")
    public ResponseEntity<DeleteCourseByNameResponse> deleteCoursebyName(@PathVariable String name) {
    	return ResponseEntity.ok(courseService.deleteCoursebyName(name));
    }
    
    @GetMapping("/getAllCourses") 
    public ResponseEntity<AllCoursesResponse> getAllCourses() {
    	
    	AllCoursesResponse allCourses = courseService.getAllCourses();
    	
    	return ResponseEntity.ok(allCourses);
    }
    
    @GetMapping("/filtercoursebyStudent/{id}") 
    public ResponseEntity<FilterCoursebyStudentResponse> filtercoursebyStudent(@PathVariable Integer id) {
    	
    	FilterCoursebyStudentResponse allCourses = courseService.filtercoursebyStudent(id);
    	
    	return ResponseEntity.ok(allCourses);
    }
    
    

}
