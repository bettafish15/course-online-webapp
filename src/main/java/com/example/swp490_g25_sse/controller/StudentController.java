package com.example.swp490_g25_sse.controller;

import java.util.List;

import com.example.swp490_g25_sse.model.Course;
import com.example.swp490_g25_sse.model.User;
import com.example.swp490_g25_sse.service.CourseService;
import com.example.swp490_g25_sse.service.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author bettafish15
 */
@Controller
@RequestMapping("/app/student")
public class StudentController {

    @Autowired
    private CourseService courseService;

    @GetMapping("")
    private String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetailsService userDetails = (CustomUserDetailsService) auth.getPrincipal();

        Page<Course> top4Course = courseService.getMostEnrolledCourses();
        List<Course> courses = top4Course.getContent();

        model.addAttribute("userName", userDetails.getUser().getFirstName());
        model.addAttribute("courses", courses);
        model.addAttribute("user", "student");

        // System.out.println(top4Course.getContent().get(0).getImageUrl());
        return "student/course-screen";
    }

    @GetMapping("/course/{id}")
    private String courseOverview(@PathVariable String id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetailsService userDetails = (CustomUserDetailsService) auth.getPrincipal();

        Course course = courseService.getCourseById(Long.parseLong(id)).get();

        model.addAttribute("userName", userDetails.getUser().getFirstName());
        model.addAttribute("course", course);

        // System.out.println(top4Course.getContent().get(0).getImageUrl());
        return "student/course-overview";
    }

    @GetMapping("/learn/{id}")
    private String learn(@PathVariable String id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetailsService userDetails = (CustomUserDetailsService) auth.getPrincipal();

        Course course = courseService.getCourseById(Long.parseLong(id)).get();

        model.addAttribute("userName", userDetails.getUser().getFirstName());
        model.addAttribute("course", course);

        // System.out.println(top4Course.getContent().get(0).getImageUrl());
        return "student/learn";
    }
}
