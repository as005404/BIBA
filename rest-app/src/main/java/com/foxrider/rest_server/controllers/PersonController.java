package com.foxrider.rest_server.controllers;

import com.foxrider.entity.Person;
import com.foxrider.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class PersonController {

    private final PersonService service;

    private final static Logger LOG = LoggerFactory.getLogger(ShiftController.class);

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/persons")
    List<Person> getPeople(Model model) {
        LOG.debug("getPeople()");
        return service.findAll();
    }

    @GetMapping("/persons/{id}")
    ResponseEntity<Object> getPersonById(Model model, @PathVariable Integer id) {
        LOG.debug("getPersonById() {}", id);
        return new ResponseEntity<Object>(service.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person by id " + id + " not found")), HttpStatus.OK);
    }

    @PostMapping(value = "/persons/", consumes = "application/json", produces = "application/json")
    ResponseEntity<Person> createPerson(Model model, @RequestBody Person person) {
        LOG.debug("createPerson() {}", person);
        return new ResponseEntity<>(service.create(person), HttpStatus.CREATED);
    }

    @PutMapping(value = "/persons/", consumes = "application/json", produces = "application/json")
    ResponseEntity<Person> updatePerson(Model model, @RequestBody Person person) {
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
