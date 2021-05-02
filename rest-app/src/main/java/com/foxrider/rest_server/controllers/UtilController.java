package com.foxrider.rest_server.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class UtilController {

    //TODO: If i will have time - add setting of site using role
    @GetMapping("/getRoles")
    Set<String> getRoles(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet());
    }
}
