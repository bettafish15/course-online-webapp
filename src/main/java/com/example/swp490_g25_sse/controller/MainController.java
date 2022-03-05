/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.swp490_g25_sse.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author ADMIN
 */
@Controller
public class MainController {

    @GetMapping("/")
    private String index() {
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
            if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_TEACHER"))){
                return "redirect:/app/teacher";
            }
            else if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STUDENT"))){
                return "redirect:/app/student";
            }else {
                return "login";
            }

            
        } else {
            return "login";
        }
    }
    
    @GetMapping("/accountInfo")
    private String accountInfo() {
        return "account-info";
    }
    
    @GetMapping("/accountSetting")
    private String accountSetting() {
        return "account-setting";
    }
}
