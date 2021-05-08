package com.foxrider.web_app.controllers;

import com.foxrider.entity.Access;
import com.foxrider.entity.Person;
import com.foxrider.rest_client.AccessRestClient;
import com.foxrider.rest_client.PersonRestClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class PersonAccessController {

    private final PersonRestClient personRestClient;
    private final AccessRestClient accessRestClient;

    public PersonAccessController(PersonRestClient personRestClient, AccessRestClient accessRestClient) {
        this.personRestClient = personRestClient;
        this.accessRestClient = accessRestClient;
    }

    @GetMapping("/persons")
    String getPeople(Model model,
                     @CookieValue(value = "jwt-cookie", defaultValue = "null") String jwtCookie) {

        List<EmailAccess> peopleAccess = personRestClient
                .findAll(jwtCookie)
                .stream()
                .map(x ->
                        new EmailAccess(x.getUserEmail(),
                                x.getRoles().stream().findFirst().get().getAccessName()))
                .collect(Collectors.toList());
        model.addAttribute("people", peopleAccess);
        return "persons";

    }

    @GetMapping(value = "/persons/{id}")
    public String getFormOfEditPeople(@PathVariable String id,
                                      Model model,
                                      @CookieValue(value = "jwt-cookie", defaultValue = "null") String jwtCookie) {
        Person person = personRestClient.findByEmail(id, jwtCookie).get();
        model.addAttribute("person", new EmailAccess(person.getUserEmail(), person.getRoles().stream().findAny().get().getAccessName()));
        model.addAttribute("Roles", accessRestClient.findAll(jwtCookie));
        return "person";

    }

    @PostMapping(value = "persons/{id}")
    public String editPerson(EmailAccess emailAccess,
                             @CookieValue(value = "jwt-cookie", defaultValue = "null") String jwtCookie,
                             @PathVariable("id") String id) {

        Person person = personRestClient.findByEmail(emailAccess.getEmail(), jwtCookie).get();
        person.setRoles(Set.of(new Access( emailAccess.getAccess())));
        personRestClient.update(person, jwtCookie);
        return "redirect:/persons";
    }

    private static class EmailAccess {
        String email;
        String access;

        public EmailAccess(String email, String access) {
            this.email = email;
            this.access = access;
        }

        public String getEmail() {
            return email;
        }

        public String getAccess() {
            return access;
        }
    }

}
