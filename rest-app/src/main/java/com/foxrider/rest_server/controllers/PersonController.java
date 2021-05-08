package com.foxrider.rest_server.controllers;

import com.foxrider.entity.Access;
import com.foxrider.entity.Person;
import com.foxrider.service.AccessService;
import com.foxrider.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class PersonController {

    private final PersonService service;
    private final AccessService accessService;

    private final static Logger LOG = LoggerFactory.getLogger(ShiftController.class);

    @Autowired
    public PersonController(PersonService service, AccessService accessService) {
        this.service = service;
        this.accessService = accessService;
    }

    @GetMapping("/persons")
    List<Person> getPeople(Model model) {
        LOG.debug("getPeople()");
        return service.findAll();
    }

    @GetMapping("/persons/{name}")
    ResponseEntity<Object> getPersonByName(Model model, @PathVariable String name) {
        LOG.debug("getPersonByName() {}", name);

        Person person = service.findByEmail(name).get();
        person.setUserPasswordHash("1124");
        return new ResponseEntity<Object>(Optional.ofNullable(person)
                .orElseThrow(() -> new EntityNotFoundException("Person by id " + name + " not found")), HttpStatus.OK);
    }

    @PostMapping(value = "/persons", consumes = "application/json", produces = "application/json")
    ResponseEntity<Person> createPerson(Model model, @RequestBody @Valid Person person) {
        LOG.debug("createPerson() {}", person);
        return new ResponseEntity<>(service.create(person), HttpStatus.CREATED);
    }

    @PutMapping(value = "/persons", consumes = "application/json", produces = "application/json")
    ResponseEntity<Person> updatePerson(Model model, @RequestBody @Valid Person person) {
        String userPasswordHash = service.findById(person.getUserId()).get().getUserPasswordHash();
        Access access = accessService.findByName(person.getRoles().stream().findAny().get().getAccessName()).get();
        person.setUserPasswordHash(userPasswordHash);
        person.setRoles(Set.of(access));
        LOG.debug("updatePerson() {}", person);
        return new ResponseEntity<>(service.update(person), HttpStatus.OK);
    }

    @DeleteMapping(value = "/persons/{id}")
    ResponseEntity<Object> deletePerson(Model model, @PathVariable Integer id) {
        LOG.debug("deletePerson() {}", id);
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
