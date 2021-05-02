package com.foxrider.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestThymeleaf {

    @GetMapping(value = {"/", "/index"})
    public String getIndex(){
        return "index";
    }



}
