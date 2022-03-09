/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.swp490_g25_sse.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bettafish15
 */
@RestController
@RequestMapping("course")
public class CourseController {

    @GetMapping(name = "/{id}", produces = "application/json")
    public String getBook(@PathVariable long id) {
        System.out.println("_____________________________________________________________");
        return "lksadfjasdfjlasdjklf";
    }
}
