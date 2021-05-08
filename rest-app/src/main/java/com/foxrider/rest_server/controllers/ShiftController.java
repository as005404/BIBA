package com.foxrider.rest_server.controllers;

import com.foxrider.entity.Shift;
import com.foxrider.service.ShiftService;
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

@RestController
public class ShiftController {

    private final ShiftService service;

    private final static Logger LOG = LoggerFactory.getLogger(ShiftController.class);

    @Autowired
    public ShiftController(ShiftService service) {
        this.service = service;
    }

    @GetMapping("/shifts")
    List<Shift> getShifts(Model model) {
        LOG.debug("getShifts()");
        return service.findAll();
    }

    @GetMapping("/shifts/{name}")
    ResponseEntity<Object> getShiftByName(Model model, @PathVariable String name) {
        LOG.debug("getShiftByName() {}", name);
        return new ResponseEntity<Object>(service.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Shift by id " + name + " not found")), HttpStatus.OK);
    }

    @PostMapping(value = "/shifts", consumes = "application/json", produces = "application/json")
    ResponseEntity<Shift> createShift(Model model, @RequestBody @Valid Shift shift) {
        LOG.debug("createShift() {}", shift);
        return new ResponseEntity<>(service.create(shift), HttpStatus.CREATED);
    }

    @PutMapping(value = "/shifts", consumes = "application/json", produces = "application/json")
    ResponseEntity<Shift> updateShift(Model model, @RequestBody @Valid Shift shift) {
        LOG.debug("updateShift() {}", shift);
        return new ResponseEntity<>(service.update(shift), HttpStatus.OK);
    }

    @DeleteMapping(value = "/shifts/{id}")
    ResponseEntity<Object> deleteShift(Model model, @PathVariable Integer id) {
        LOG.debug("deleteShift() {}", id);
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
