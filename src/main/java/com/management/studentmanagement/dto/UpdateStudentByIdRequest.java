package com.management.studentmanagement.dto;

import com.management.studentmanagement.entity.Course;
import com.management.studentmanagement.entity.Role;
import com.management.studentmanagement.entity.Token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentByIdRequest {
	
	  private String firstname;
	  private String lastname;
	  private String email;

}
