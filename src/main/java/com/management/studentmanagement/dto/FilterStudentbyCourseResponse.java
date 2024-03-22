package com.management.studentmanagement.dto;

import com.management.studentmanagement.entity.Course;
import com.management.studentmanagement.entity.Student;
import com.management.studentmanagement.model.FilterStudentModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterStudentbyCourseResponse{
	
	private FilterStudentModel[] students;
	private String message;


}
