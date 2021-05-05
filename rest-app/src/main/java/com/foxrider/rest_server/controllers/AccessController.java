package com.foxrider.rest_server.controllers;

import com.foxrider.entity.Access;
import com.foxrider.service.AccessService;
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
public class AccessController {

    private final AccessService service;

    private final static Logger LOG = LoggerFactory.getLogger(ShiftController.class);

    @Autowired
    public AccessController(AccessService service) {
        this.service = service;
    }

    @GetMapping("/accesses")
    List<Access> getAccesses(Model model) {
        LOG.debug("getAccesses()");
        return service.findAll();
    }

    @GetMapping("/accesses/{id}")
    ResponseEntity<Object> getAccessById(Model model, @PathVariable Integer id) {
        LOG.debug("getAccessById() {}", id);
        return new ResponseEntity<Object>(service.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Access by id " + id + " not found")), HttpStatus.OK);
    }

    @PostMapping(value = "/accesses", consumes = "application/json", produces = "application/json")
    ResponseEntity<Access> createAccess(Model model, @RequestBody Access access) {
        LOG.debug("createAccess() {}", access);
        return new ResponseEntity<>(service.create(access), HttpStatus.CREATED);
    }

    @PutMapping(value = "/accesses", consumes = "application/json", produces = "application/json")
    ResponseEntity<Access> updateAccess(Model model, @RequestBody Access access) {
        LOG.debug("updateAccess() {}", access);
        return new ResponseEntity<>(service.update(access), HttpStatus.OK);
    }

    @DeleteMapping(value = "/accesses/{id}")
    ResponseEntity<Object> deleteAccess(Model model, @PathVariable Integer id) {
        LOG.debug("deleteAccess() {}", id);
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
