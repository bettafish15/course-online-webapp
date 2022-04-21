/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.swp490_g25_sse.controller;

import com.example.swp490_g25_sse.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.example.swp490_g25_sse.dto.AccountInfoDto;
import com.example.swp490_g25_sse.dto.UserInfoDto;
import com.example.swp490_g25_sse.model.Course;
import com.example.swp490_g25_sse.model.Student;
import com.example.swp490_g25_sse.service.CourseService;
import com.example.swp490_g25_sse.service.CustomUserDetailsService;
import com.example.swp490_g25_sse.service.StudentService;
import com.example.swp490_g25_sse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author ADMIN
 */
@Controller
public class MainController {

    @Autowired
    JwtUtils jwtUtils;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    private String index(Model model) {
        Page<Course> courses = courseService.get4NewestCourses();

        model.addAttribute("courses", courses);
        return "index";
    }

    @GetMapping("/login")
    private String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {

            /* The user is logged in :) */

            return "forward:/app";
        }

        return "login";
    }

    @GetMapping("/app")
    private String app() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_TEACHER"))) {
                return "redirect:/app/teacher";
            } else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STUDENT"))) {
                return "redirect:/app/student";
            } else {
                return "login";
            }

        } else {
            return "login";
        }
    }

    @GetMapping("/account-info")
    private String accountInfo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetailsService userDetails = (CustomUserDetailsService) auth.getPrincipal();
        UserInfoDto userInfo = new UserInfoDto(userDetails.getUser().getFirstName(),
                userDetails.getUser().getLastName(), userDetails.getUser().getEmail(),
                userDetails.getUser().getImageURL());

        String userImgUrl = userDetails.getUser().getImageURL();
        Student student = studentService.getStudentInfo(userDetails.getUser());
        if (userDetails.getRole().equals("ROLE_STUDENT")) {
            long numberOfFinishedCourses = courseService.getNumberOfFinishedCourse(student, true);
            long numberOfCourses = courseService.getNumberOfStudentCourses(student);
            model.addAttribute("user", "student");
            model.addAttribute("numberOfFinishedCourses", numberOfFinishedCourses);
            model.addAttribute("numberOfCourses", numberOfCourses);
        }
        if (userDetails.getRole().equals("ROLE_TEACHER")) {
            model.addAttribute("user", "teacher");
        }

        model.addAttribute("userImgUrl", userImgUrl);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("userName", userDetails.getUser().getFirstName());
        return "account-info";
    }

    @PostMapping("/account-info")
    private String updateUserInfo(@ModelAttribute("userInfo") UserInfoDto userInfo, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetailsService currentUser = (CustomUserDetailsService) auth.getPrincipal();
        if (userInfo.getEmail().equals("") || userInfo.getEmail() == null) {
            System.out.println("Error");
        }

        if (currentUser.getRole().equals("ROLE_STUDENT")) {
            model.addAttribute("user", "student");
        }
        if (currentUser.getRole().equals("ROLE_TEACHER")) {
            model.addAttribute("user", "teacher");
        }

        userService.updateInfo(userInfo, currentUser.getUser());
        return "redirect:/account-info";
    }

    @GetMapping("/account-setting")
    private String accountSetting(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetailsService userDetails = (CustomUserDetailsService) auth.getPrincipal();
        AccountInfoDto accountInfo = new AccountInfoDto();
        String userImgUrl = userDetails.getUser().getImageURL();
        UserInfoDto userInfo = new UserInfoDto(userDetails.getUser().getFirstName(),
                userDetails.getUser().getLastName(), userDetails.getUser().getEmail(),
                userDetails.getUser().getImageURL());
        model.addAttribute("accountInfo", accountInfo);
        if (userDetails.getRole().equals("ROLE_STUDENT")) {
            model.addAttribute("user", "student");
        }
        if (userDetails.getRole().equals("ROLE_TEACHER")) {
            model.addAttribute("user", "teacher");
        }
        model.addAttribute("userImgUrl", userImgUrl);
        model.addAttribute("userName", userDetails.getUser().getFirstName());
        model.addAttribute("userInfo", userInfo);
        return "account-setting";
    }

    @PostMapping("/account-setting")
    private String updateAccountInfo(@ModelAttribute("accountInfo") AccountInfoDto accountInfo) {
        try {
            userService.updateAccount(accountInfo);
        } catch (ResponseStatusException e) {
            System.out.println(e.getMessage());
            return "redirect:/account-setting?error=".concat(e.getReason());
        }

        System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDd");

        return "redirect:/account-setting";
    }
}
