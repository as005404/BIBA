package com.foxrider.rest_server.controllers;

import com.foxrider.entity.ValueOfSensors;
import com.foxrider.service.ValueOfSensorService;
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
public class ValueOfSensorController {
    private final static Logger LOG = LoggerFactory.getLogger(ValueOfSensorController.class);
    private final ValueOfSensorService service;

    @Autowired
    public ValueOfSensorController(ValueOfSensorService service) {
        this.service = service;
    }

    @GetMapping("/values")
    List<ValueOfSensors> getValues(Model model) {
        LOG.debug("getValues()");
        return service.findAll();
    }

    @GetMapping("/values/{id}")
    ResponseEntity<Object> getValueById(Model model, @PathVariable Integer id) {
        LOG.debug("getValueById() {}", id);
        return new ResponseEntity<Object>(service.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Value by id " + id + " not found")), HttpStatus.OK);
    }

    @PostMapping(value = "/values", consumes = "application/json", produces = "application/json")
    ResponseEntity<ValueOfSensors> createValue(Model model, @RequestBody ValueOfSensors value) {
        LOG.debug("createValue() {}", value);
        return new ResponseEntity<>(service.create(value), HttpStatus.CREATED);
    }

    @PutMapping(value = "/values", consumes = "application/json", produces = "application/json")
    ResponseEntity<ValueOfSensors> updateValue(Model model, @RequestBody ValueOfSensors value) {
        LOG.debug("updateValue() {}", value);
        return new ResponseEntity<>(service.update(value), HttpStatus.OK);
    }

    @DeleteMapping(value = "/values/{id}")
    ResponseEntity<Object> deleteValue(Model model, @PathVariable Integer id) {
        LOG.debug("deleteValue() {}", id);
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
