package com.management.studentmanagement.service;

import com.management.studentmanagement.entity.Course;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.management.studentmanagement.common.CourseStudentCommon;
import com.management.studentmanagement.common.ResponseMessage;
import com.management.studentmanagement.config.JwtService;
import com.management.studentmanagement.dto.AllStudentsResponse;
import com.management.studentmanagement.dto.AssignCourseDTO;
import com.management.studentmanagement.dto.AssignCourseToStudentResponse;
import com.management.studentmanagement.dto.DeleteCourseDTO;
import com.management.studentmanagement.entity.Role;
import com.management.studentmanagement.entity.Student;
import com.management.studentmanagement.entity.Token;
import com.management.studentmanagement.entity.TokenType;
import com.management.studentmanagement.model.FilterStudentModel;
import com.management.studentmanagement.repository.CourseRepository;
import com.management.studentmanagement.repository.StudentRepository;
import com.management.studentmanagement.repository.TokenRepository;
import com.management.studentmanagement.repository.UserRepository;
import com.management.studentmanagement.dto.DeleteCourseFromStudentResponse;
import com.management.studentmanagement.dto.FilterCoursebyStudentResponse;
import com.management.studentmanagement.dto.FilterStudentbyCourseResponse;
import com.management.studentmanagement.dto.StudentbyIdResponse;
import com.management.studentmanagement.dto.UpdateStudentByIdRequest;
import com.management.studentmanagement.dto.UpdateStudentByIdResponse;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository student_Repo;
	
	@Autowired
	private CourseRepository course_repo;
	
	
	public AllStudentsResponse getAllStudents() {
		
		AllStudentsResponse resp;
		
		List<Student> allStudents = student_Repo.findAll();
		
		Student[] s_array = allStudents.toArray(new Student[0]);
		
		resp = AllStudentsResponse.builder()
				.students(s_array)
				.message(ResponseMessage.SUCCESSFUL)
				.build();
		
		return resp;
	} 
	
	public AssignCourseToStudentResponse assignCourseToStudent(Integer id,AssignCourseDTO request) {
		
		AssignCourseToStudentResponse resp;
		
		Optional<Student> st = student_Repo.findById(id);
		
		if(st.isEmpty()) {
			
			resp = AssignCourseToStudentResponse.builder().message(ResponseMessage.STUDENT_NOT_FOUND).build();
			return resp;
			
		}
		
		Optional<Course> course = course_repo.findByCourseName(request.getCourseName());
		
		if(course.isEmpty()) {
			resp = AssignCourseToStudentResponse.builder().message(ResponseMessage.COURSE_NOT_FOUND).build();
			
			return resp;
		}
		
		if(st.get().getCourses().contains(course.get())) {
			
			resp = AssignCourseToStudentResponse.builder().message(ResponseMessage.THE_STUDENT_ALREADY_HAS_THE_COURSE).build();
		
			return resp;
		}
		
		if(st.get().getCourses().size()>=Integer.parseInt(CourseStudentCommon.MAX_COURSE) || course.get().getStudents().size() >= Integer.parseInt(CourseStudentCommon.MAX_STUDENT)) {
			
			if(st.get().getCourses().size()>=Integer.parseInt(CourseStudentCommon.MAX_COURSE) ) {
				
				resp = AssignCourseToStudentResponse.builder().message(ResponseMessage.MAXIMUM_COURSES_THAT_CAN_BE_TAKEN+" :" + CourseStudentCommon.MAX_COURSE).build();
				
				return resp;
			}
			
			if(course.get().getStudents().size() >= Integer.parseInt(CourseStudentCommon.MAX_STUDENT)) {
				
				resp = AssignCourseToStudentResponse.builder().message(ResponseMessage.MAXIMUM_NUMBER_OF_STUDENTS_POSSIBLE+" :" + CourseStudentCommon.MAX_STUDENT).build();
				
				return resp;
			}
	
		}
		
		st.get().getCourses().add(course.get());
		course.get().getStudents().add(st.get());
		
		student_Repo.save(st.get());
		course_repo.save(course.get());
		
		resp = AssignCourseToStudentResponse.builder().message(ResponseMessage.SUCCESSFUL).build();
		
		return resp;
	} 
	
	public DeleteCourseFromStudentResponse deleteCourseFromStudent(Integer id, DeleteCourseDTO request) {
		
		DeleteCourseFromStudentResponse resp;
		
		Optional<Student> st = student_Repo.findById(id);
		
		if(st.isEmpty()) {
			
			resp = DeleteCourseFromStudentResponse.builder().message(ResponseMessage.STUDENT_NOT_FOUND).build();
			
			return resp;
		}
		
		Optional<Course> course = course_repo.findByCourseName(request.getCourseName());
		
		if(course.isEmpty()) {
			resp = DeleteCourseFromStudentResponse.builder().message(ResponseMessage.COURSE_NOT_FOUND).build();
			
			return resp;
		}
		
		if(!st.get().getCourses().contains(course.get())) {
			
			resp = DeleteCourseFromStudentResponse.builder().message(ResponseMessage.THE_STUDENT_DOESNT_HAVE_THE_COURSE).build();
			
			return resp;
		}
		
		st.get().getCourses().remove(course.get());
		course.get().getStudents().remove(st.get());
		
		student_Repo.save(st.get());
		course_repo.save(course.get());
		
		resp = DeleteCourseFromStudentResponse.builder().message(ResponseMessage.SUCCESSFUL).build();
		
		return resp;
	}

	public StudentbyIdResponse getStudentbyId(Integer id) {
		
		Optional<Student> st = student_Repo.findById(id);
		
		if(st.isEmpty()) {
			StudentbyIdResponse resp = StudentbyIdResponse.builder()
			.message(ResponseMessage.STUDENT_NOT_FOUND)
			.build();
			
			return resp;
		}
		
		Student st_ = st.get();
		
		StudentbyIdResponse resp = StudentbyIdResponse.builder()
		.courses(st_.getCourses().toArray(new Course[0]))
		.email(st_.getEmail())
		.firstname(st_.getFirstname())
		.id(st_.getId())
		.lastname(st_.getLastname())
		.tokens(st_.getTokens().toArray((new Token[0])))
		.message(ResponseMessage.SUCCESSFUL)
		.role(st_.getRole().name())
		.build();
		
		return resp;
	}

	public UpdateStudentByIdResponse updateStudentById(Integer id, UpdateStudentByIdRequest request) {
		
		Optional<Student> st = student_Repo.findById(id);
		
		if(st.isEmpty()) {
			UpdateStudentByIdResponse resp = UpdateStudentByIdResponse.builder()
			.message(ResponseMessage.STUDENT_NOT_FOUND)
			.build();
			
			return resp;
		}
		
		Student st_ = st.get();
		
		st_.setEmail(request.getEmail());
		st_.setFirstname(request.getFirstname());
		st_.setLastname(request.getLastname());
		
		//ROLE güncellemeleri JWT tarafını etkiler mi detaylıca bakmak gerek
		
		
		student_Repo.save(st_);
		
		UpdateStudentByIdResponse resp = UpdateStudentByIdResponse.builder()
				.email(st_.getEmail())
				.firstname(st_.getFirstname())
				.lastname(st_.getLastname())
				.id(st_.getId())
				.message(ResponseMessage.SUCCESSFUL)
				.build();
		
		return resp;
	}

	public FilterStudentbyCourseResponse filterStudentbyCourse(Integer id) {
		

		FilterStudentbyCourseResponse resp ;
		
		Optional<Course> course = course_repo.findById(id);
		
		if(course.isEmpty()) {
			resp = 	FilterStudentbyCourseResponse.builder()
					.message(ResponseMessage.COURSE_NOT_FOUND)
					.students(new FilterStudentModel[0])
					.build();
			
			return resp;
		}
		
		Course course_ = course.get();
		
		if(course_.getStudents().size() == 0) {
			
			resp = 	FilterStudentbyCourseResponse.builder()
					.message(ResponseMessage.THE_COURSE_DOES_NOT_HAVE_ANY_STUDENT)
					.students(new FilterStudentModel[0])
					.build();
			return resp; 
		}
		else {
			
			//db'den student'in password, token gibi bilgileride çekiyordu. 
			//bunları göstermek gereksiz olduğu için yeni bir model'e mapledim
			
			Set<FilterStudentModel> filterModelSet = new HashSet<FilterStudentModel>();
			
			course_.getStudents().forEach(s -> {
				filterModelSet.add(FilterStudentModel.builder()
						.email(s.getEmail())
						.firstName(s.getFirstname())
						.id(s.getId())
						.lastName(s.getLastname())
						.role(s.getRole().name())
						.username(s.getUsername())
						.build());
			});
			
			resp = 	FilterStudentbyCourseResponse.builder()
					.students(filterModelSet.toArray(new FilterStudentModel[0]))
					.message(ResponseMessage.SUCCESSFUL)
					.build();
			
			return resp;
		}
	}
	

}
