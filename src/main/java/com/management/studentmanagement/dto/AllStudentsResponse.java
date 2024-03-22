package com.management.studentmanagement.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.management.studentmanagement.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllStudentsResponse {
	
	  private Student[] students;
	  
	  private String message;
	  

}
