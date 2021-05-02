package com.foxrider.web_app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthenticationController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    @GetMapping( "/login")
    public String getLoginPage(Model model){
        model.addAttribute("usernameAndPassword", new UsernameAndPassword());
        return "login";
    }

    @PostMapping( "/login")
    public String authorizeUser(Model model, UsernameAndPassword usernameAndPassword){

        return "redirect:/";
    }

    static class UsernameAndPassword{
        private String username;
        private String password;

        public UsernameAndPassword() {
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

}
