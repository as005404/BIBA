package com.foxrider.web_app.controllers;

import com.foxrider.entity.UsernameAndPassword;
import com.foxrider.service.LoginAndRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthenticationController {
    private final LoginAndRegisterService service;

    @Autowired
    public AuthenticationController(LoginAndRegisterService service) {
        this.service = service;
    }

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    @GetMapping("/login")
    public String getLoginPage(Model model, HttpServletResponse response) {
        String authenticate = response.getHeader("Authenticate");
        model.addAttribute("usernameAndPassword", new UsernameAndPassword());
        return "login";
    }

    @PostMapping("/login")
    public String authorizeUser(Model model, UsernameAndPassword usernameAndPassword, HttpServletResponse response) {
        ResponseEntity<Object> login = service.login(usernameAndPassword);
        String jwtAuthorization = login.getHeaders().get("Authorization").get(0);

        String token = jwtAuthorization.replace("Bearer ", "");

        Cookie jwtCookie = new Cookie("jwt-cookie", token);
        jwtCookie.setMaxAge(14 * 24 * 60 * 60);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpServletResponse response) {
        Cookie jwtCookie = new Cookie("jwt-cookie", null);
        jwtCookie.setMaxAge(0);
        response.addCookie(jwtCookie);

        return "redirect:/";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("usernameAndPassword", new UsernameAndPassword());
        return "signup";
    }

    @PostMapping("/register")
    public String RegisterUser(Model model, UsernameAndPassword usernameAndPassword) {
        service.register(usernameAndPassword);
        return "redirect:/";
    }

}
