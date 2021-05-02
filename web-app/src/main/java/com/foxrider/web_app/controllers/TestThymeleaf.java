package com.foxrider.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestThymeleaf {

    @GetMapping(value = {"/", "/index"})
    public String getIndex(Model model,
                           @CookieValue(value = "jwt-cookie", defaultValue = "hello") String jwtCookie) {
        model.addAttribute("cookie", jwtCookie);
        return "index";
    }


}
