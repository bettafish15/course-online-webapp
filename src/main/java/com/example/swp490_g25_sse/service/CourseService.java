/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.swp490_g25_sse.service;

import com.example.swp490_g25_sse.dto.CourseDto;
import com.example.swp490_g25_sse.model.Course;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author bettafish15
 */
public interface CourseService {

	Optional<Course> getCourseById(long id);

	Course createCourse(CourseDto courseDto);

	List<Course> getCourses();

	Optional<Course> deleteCourse(long id);

	Course updateCourse(CourseDto courseDto, long id);

	Page<Course> getMostEnrolledCourses();
}
