package com.foxrider.rest_server.controllers;

import com.foxrider.entity.Person;
import com.foxrider.entity.UsernameAndPassword;
import com.foxrider.service.AccessService;
import com.foxrider.service.PersonService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@Controller
public class AuthenticationController {

    private final PersonService personService;
    private final AccessService accessService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationController(PersonService personService, AccessService accessService, PasswordEncoder passwordEncoder) {
        this.personService = personService;
        this.accessService = accessService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    ResponseEntity<Object> RegisterUser(@RequestBody UsernameAndPassword uap) {
        personService.create(new Person(
                        uap.getUsername(),
                        passwordEncoder.encode(uap.getPassword()),
                        RandomStringUtils.randomAlphabetic(16),
                        Set.of(accessService.findByName("User").get())
                )
        );
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
