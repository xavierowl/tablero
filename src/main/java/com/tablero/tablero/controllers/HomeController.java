package com.tablero.tablero.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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
