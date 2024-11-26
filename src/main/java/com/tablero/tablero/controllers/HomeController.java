package com.tablero.tablero.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/private")
public class HomeController {

    @RequestMapping("/home")
    public String getHome(){
        return "home";
    }

    @RequestMapping("/")
    public String getRoot(){
        return "home";
    }
}
