package com.management.studentmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.studentmanagement.common.ResponseMessage;
import com.management.studentmanagement.dto.AllCoursesResponse;
import com.management.studentmanagement.dto.CreateCourseRequest;
import com.management.studentmanagement.dto.CreateCourseResponse;
import com.management.studentmanagement.dto.DeleteCourseByIdResponse;
import com.management.studentmanagement.dto.DeleteCourseByNameResponse;
import com.management.studentmanagement.dto.FilterCoursebyStudentResponse;
import com.management.studentmanagement.entity.Course;
import com.management.studentmanagement.entity.Student;
import com.management.studentmanagement.repository.CourseRepository;
import com.management.studentmanagement.repository.StudentRepository;
import com.management.studentmanagement.repository.TokenRepository;
import com.management.studentmanagement.repository.UserRepository;

@Service
public class CourseService {
	
	@Autowired
	private StudentRepository student_Repo;
	
	@Autowired
	private CourseRepository course_repo;


    public CreateCourseResponse createCourse(CreateCourseRequest request) {
    	
    	CreateCourseResponse resp = new CreateCourseResponse();
    	
		Optional<Course> courseCheck = course_repo.findByCourseName(request.getCourseName());
		
		if(!courseCheck.isEmpty()) {
			
			resp.setMessage(ResponseMessage.THE_COURSE_ALREADY_EXISTS);
			
			return resp;
		}
    	
    	Course course = Course.builder()
    	.courseName(request.getCourseName())
    	.build();
    	
    	course_repo.save(course);
    	
    	resp.setMessage(ResponseMessage.SUCCESSFUL);
    	
        return resp;
    }


	public DeleteCourseByIdResponse deleteCoursebyId(Integer id) {
		
		DeleteCourseByIdResponse resp = new DeleteCourseByIdResponse();
		
		Optional<Course> courseCheck = course_repo.findById(id);
		
		if(courseCheck.isEmpty()) {
			resp.setMessage(ResponseMessage.COURSE_NOT_FOUND);
			
			return resp;
		}
		
		if(!courseCheck.get().getStudents().isEmpty()) {
			resp.setMessage(ResponseMessage.THERE_ARE_STUDENTS_ENROLLED_IN_THE_COURSE_IT_CANNOT_BE_DELETED);
			//kurstaki ogrencileri kurstan cıkarip silinebilir
			return resp;
		}
		
		course_repo.deleteById(id);
		resp.setMessage(ResponseMessage.SUCCESSFUL);
		
		return resp;
	}
	
	public DeleteCourseByNameResponse deleteCoursebyName(String name) {
		
		DeleteCourseByNameResponse resp = new DeleteCourseByNameResponse();
		
		Optional<Course> courseCheck = course_repo.findByCourseName(name);
		
		if(courseCheck.isEmpty()) {
			resp.setMessage(ResponseMessage.COURSE_NOT_FOUND);
			
			return resp;
		}
		
		if(!courseCheck.get().getStudents().isEmpty()) {
			resp.setMessage(ResponseMessage.THERE_ARE_STUDENTS_ENROLLED_IN_THE_COURSE_IT_CANNOT_BE_DELETED);
			
			return resp;
		}
		
		course_repo.deleteByCourseName(name);
		resp.setMessage(ResponseMessage.SUCCESSFUL);
		
		return resp;
	}


	public AllCoursesResponse getAllCourses() {
		
		AllCoursesResponse resp;
		
		List<Course> allCourses = course_repo.findAll();
		
		Course[] array = allCourses.toArray(new Course[0]);
		
		resp = AllCoursesResponse.builder()
				.courses(array)
				.message(ResponseMessage.SUCCESSFUL)
				.build();
		
		return resp;
	}
	
	public FilterCoursebyStudentResponse filtercoursebyStudent(Integer id) {
		
		
		
		FilterCoursebyStudentResponse resp ;
		
		Optional<Student> st = student_Repo.findById(id);
		
		if(st.isEmpty()) {
			resp = 	FilterCoursebyStudentResponse.builder()
					.message(ResponseMessage.STUDENT_NOT_FOUND)
					.courses(new Course[0])
					.build();
			
			return resp;
		}
		
		Student st_ = st.get();
		
		if(st_.getCourses().size() == 0) {
			
			resp = 	FilterCoursebyStudentResponse.builder()
					.message(ResponseMessage.THE_STUDENT_DOES_NOT_HAVE_ANY_COURSES)
					.courses(new Course[0])
					.build();
			return resp; 
		}
		else {
			resp = 	FilterCoursebyStudentResponse.builder()
			.courses(st_.getCourses().toArray(new Course[0]))
			.message(ResponseMessage.SUCCESSFUL)
			.build();
			
			return resp;
		}
		
		
	}
	
	
	//kurs update yazılaacak unutma

}
