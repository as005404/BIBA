package com.foxrider.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/", "/index"})
    public String getIndex(Model model,
                           @CookieValue(value = "jwt-cookie", defaultValue = "null") String jwtCookie) {
        model.addAttribute("cookie", jwtCookie);
        model.addAttribute("isCookiePresent", !jwtCookie.equalsIgnoreCase("null"));
        return "index";
    }


}
