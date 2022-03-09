package com.example.swp490_g25_sse.controller.error;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppErrorController implements ErrorController{
    private final static String PATH = "/error";

    @RequestMapping(PATH)
    public String getErrorPath(HttpServletRequest request) {
        // TODO Auto-generated method stub
        return "error";
    }

}