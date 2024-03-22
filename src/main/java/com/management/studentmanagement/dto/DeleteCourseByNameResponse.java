package com.management.studentmanagement.dto;

import com.management.studentmanagement.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCourseByNameResponse{
	
	private String message;
}
