/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.swp490_g25_sse.controller.api;

import com.example.swp490_g25_sse.dto.CourseDto;
import com.example.swp490_g25_sse.dto.UserRegistrationDto;
import com.example.swp490_g25_sse.exception.BaseRestException;
import com.example.swp490_g25_sse.model.Course;
import com.example.swp490_g25_sse.model.Teacher;
import com.example.swp490_g25_sse.service.CourseService;
import com.example.swp490_g25_sse.service.CustomUserDetailsService;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author bettafish15
 */
@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public Course getCourseById(@PathVariable long id) {
        Optional<Course> course = courseService.getCourseById(id);
        if (course.isEmpty()) {
            throw new BaseRestException(HttpStatus.NOT_FOUND, "Not Found");
        }
        return course.get();
    }

    @GetMapping(produces = "application/json")
    public List<Course> queryCourses() {
        return courseService.getCourses();
    }
    //

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Course createCourse(@RequestBody CourseDto dto) {
        System.out.println("=============================================================================");
        Course course = courseService.createCourse(dto);

        return course;
    }

    //
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public Course deleteCourse(@PathVariable long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetailsService currentUser = (CustomUserDetailsService) auth.getPrincipal();

        Optional<Course> course = courseService.getCourseById(id);

        if (course.isEmpty()) {
            throw new BaseRestException(HttpStatus.NOT_FOUND, "cant find this course to delete");
        }

        Teacher teacher = course.get().getTeacher();
        if (teacher.getUser().getId() != currentUser.getUser().getId()) {
            throw new BaseRestException(HttpStatus.FORBIDDEN, "you cant delete this course");
        }

        Optional<Course> result = courseService.deleteCourse(id);

        return result.get();
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public Course updateCourse(@PathVariable long id, @RequestBody CourseDto dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetailsService currentUser = (CustomUserDetailsService) auth.getPrincipal();

        Optional<Course> course = courseService.getCourseById(id);

        if (course.isEmpty()) {
            throw new BaseRestException(HttpStatus.NOT_FOUND, "cant find this course to edit");
        }

        Teacher teacher = course.get().getTeacher();

        if (teacher.getUser().getId() != currentUser.getUser().getId()) {
            throw new BaseRestException(HttpStatus.FORBIDDEN, "you cant edit this course");
        }

        Course result = courseService.updateCourse(dto, id);

        return result;
    }
}
