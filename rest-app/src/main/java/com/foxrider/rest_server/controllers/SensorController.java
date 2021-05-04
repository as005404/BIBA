package com.foxrider.rest_server.controllers;

import com.foxrider.entity.Sensor;
import com.foxrider.service.SensorService;
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
public class SensorController {
    private final SensorService service;

    private final static Logger LOG = LoggerFactory.getLogger(SensorController.class);

    @Autowired
    public SensorController(SensorService service) {
        this.service = service;
    }

    @GetMapping("/sensors")
    List<Sensor> getSensors(Model model) {
        LOG.debug("getSensors()");
        List<Sensor> all = service.findAll();
        return all;
    }

    @GetMapping("/sensors/{name}")
    ResponseEntity<Object> getSensorByName(Model model, @PathVariable String name) {
        LOG.debug("getSensorByName() {}", name);
        return new ResponseEntity<Object>(service.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Person by id " + name + " not found")), HttpStatus.OK);
    }

    @PostMapping(value = "/sensors", consumes = "application/json", produces = "application/json")
    ResponseEntity<Sensor> createSensor(Model model, @RequestBody Sensor sensor) {
        LOG.debug("createSensor() {}", sensor);
        return new ResponseEntity<>(service.create(sensor), HttpStatus.CREATED);
    }

    @PutMapping(value = "/sensors", consumes = "application/json", produces = "application/json")
    ResponseEntity<Sensor> updateSensor(Model model, @RequestBody Sensor sensor) {
        LOG.debug("updateSensor() {}", sensor);
        return new ResponseEntity<>(service.update(sensor), HttpStatus.OK);
    }

    @DeleteMapping(value = "/sensors/{id}")
    ResponseEntity<Object> deleteSensor(Model model, @PathVariable Integer id) {
        LOG.debug("deleteSensor() {}", id);
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
