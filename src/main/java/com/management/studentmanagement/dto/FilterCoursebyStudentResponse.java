package com.management.studentmanagement.dto;

import com.management.studentmanagement.entity.Course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterCoursebyStudentResponse{
	
	private Course[] courses;
	private String message;

}
