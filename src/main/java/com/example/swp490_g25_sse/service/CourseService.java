/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.swp490_g25_sse.service;

import com.example.swp490_g25_sse.dto.CourseDto;
import com.example.swp490_g25_sse.dto.CourseOverviewDto;
import com.example.swp490_g25_sse.dto.MilestoneDto;
import com.example.swp490_g25_sse.model.Course;
import com.example.swp490_g25_sse.model.Student;
import com.example.swp490_g25_sse.model.StudentCourseEnrollment;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author bettafish15
 */
public interface CourseService {

	Optional<Course> getCourseById(long id);

	Course createCourse(CourseDto courseDto);

	List<Course> getCourses();

	List<CourseOverviewDto> overview(StudentCourseEnrollment enroll);

	List<MilestoneDto> milestone(StudentCourseEnrollment enroll);

	Optional<Course> deleteCourse(long id);

	Course updateCourse(CourseDto courseDto, long id);

	Page<Course> getMostEnrolledCourses();

	List<Course> getStudentCourses(Student student, Boolean isFinished);

	Boolean isAlreadyEnrolled(Course course, Student student);

	Boolean isCourseFinished(StudentCourseEnrollment enroll);
}
