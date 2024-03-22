package com.management.studentmanagement.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.JoinColumn;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@Table(name = "_student")
@DiscriminatorValue("ent_Student")
public class Student extends User{
	
	  @JsonIgnore	
	  @ManyToMany(cascade = {
		        CascadeType.PERSIST,
		        CascadeType.MERGE
		    })
		    @JoinTable(name = "students_courses",
		        joinColumns = @JoinColumn(name = "student_id"),
		        inverseJoinColumns = @JoinColumn(name = "course_id")
	  )
	  private Set<Course> courses;

	  
	  
	public Set<Course> getCourses() {
		if(courses == null) {
			courses = new HashSet<Course>();
		}
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	  
	  



}
