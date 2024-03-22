package com.management.studentmanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.studentmanagement.dto.AllStudentsResponse;
import com.management.studentmanagement.dto.AssignCourseDTO;
import com.management.studentmanagement.dto.AssignCourseToStudentResponse;
import com.management.studentmanagement.dto.DeleteCourseDTO;
import com.management.studentmanagement.dto.StudentbyIdResponse;
import com.management.studentmanagement.dto.UpdateStudentByIdRequest;
import com.management.studentmanagement.dto.UpdateStudentByIdResponse;
import com.management.studentmanagement.service.StudentService;
import com.management.studentmanagement.dto.DeleteCourseFromStudentResponse;
import com.management.studentmanagement.dto.FilterStudentbyCourseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/student") //auth degisecek. simdilik rol bagimsiz deniyorum
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class StudentController {
	
	@Autowired
	StudentService studentservice;
	
    @GetMapping("/getAllStudents")
    public ResponseEntity<AllStudentsResponse> getAllStudents() {
    	
    	AllStudentsResponse allStudents = studentservice.getAllStudents();
    	
    	return ResponseEntity.ok(allStudents);
    }
    
    @GetMapping("/getStudentby/{id}")
    public ResponseEntity<StudentbyIdResponse> getStudentById(@PathVariable Integer id) {
    	
        return ResponseEntity.ok(studentservice.getStudentbyId(id));
    }
    
    
    @PostMapping("/assignCoursToStudent/{id}")
    public ResponseEntity<AssignCourseToStudentResponse> assignCoursToStudent(@PathVariable Integer id, @RequestBody AssignCourseDTO request) {
    	
    	AssignCourseToStudentResponse resp = studentservice.assignCourseToStudent(id,request);
    	
        return ResponseEntity.ok(resp);
    }
    
    
    @DeleteMapping("/deleteCourseFromStudent/{id}")
    public ResponseEntity<DeleteCourseFromStudentResponse> deleteCourseFromStudent(@PathVariable Integer id, @RequestBody DeleteCourseDTO request) {
    	
    	DeleteCourseFromStudentResponse resp = studentservice.deleteCourseFromStudent(id,request);
    	
        return ResponseEntity.ok(resp);
    }
    
    
    @PutMapping("/UpdateStudentById/{id}")
    public ResponseEntity<UpdateStudentByIdResponse> updateStudentById(@PathVariable Integer id, @RequestBody UpdateStudentByIdRequest request) {
    	
    	UpdateStudentByIdResponse resp = studentservice.updateStudentById(id,request);
    	
        return ResponseEntity.ok(resp);
    }
    
    @GetMapping("/filterStudentbyCourse/{id}") 
    public ResponseEntity<FilterStudentbyCourseResponse> filterStudentbyCourse(@PathVariable Integer id) {
    	
    	FilterStudentbyCourseResponse allCourses = studentservice.filterStudentbyCourse(id);
    	
    	return ResponseEntity.ok(allCourses);
    }
    
	
}
