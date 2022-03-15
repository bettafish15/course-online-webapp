/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.swp490_g25_sse.controller.api;

import com.example.swp490_g25_sse.dto.UserRegistrationDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bettafish15
 */
@RestController
@RequestMapping("/api/course")
public class CourseController {

    @GetMapping(value = "/test", produces = "application/json")
//    @PreAuthorize("hasRole('MODERATOR')")
    public Object getTest() {
        System.out.println("_____________________________________________________________");
        return new UserRegistrationDto();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public String getBook(@PathVariable long id) {
        System.out.println("_____________________________________________________________");
        return "lksadfjasdfjlasdjklf";
    }
    
    @PostMapping
    public String getPost() {
        System.out.println("_____________________________________________________________");
        return "lksadfjasdfjlasdjklf";
    }
}
