package com.foxrider.rest_server.controllers;

import com.foxrider.entity.Person;
import com.foxrider.entity.Sensor;
import com.foxrider.entity.Shift;
import com.foxrider.entity.ValueOfSensors;
import com.foxrider.service.PersonService;
import com.foxrider.service.SensorService;
import com.foxrider.service.ShiftService;
import com.foxrider.service.ValueOfSensorService;
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
public class ValueOfSensorController {
    private final static Logger LOG = LoggerFactory.getLogger(ValueOfSensorController.class);
    private final ValueOfSensorService valueOfSensorService;
    private final PersonService personService;
    private final ShiftService shiftService;
    private final SensorService sensorService;

    @Autowired
    public ValueOfSensorController(ValueOfSensorService valueOfSensorService, PersonService personService, ShiftService shiftService, SensorService sensorService) {
        this.valueOfSensorService = valueOfSensorService;
        this.personService = personService;
        this.shiftService = shiftService;
        this.sensorService = sensorService;
    }

    @GetMapping("/values")
    List<ValueOfSensors> getValues(Model model) {
        LOG.debug("getValues()");
        return valueOfSensorService.findAll();
    }

    @GetMapping("/values/{id}")
    ResponseEntity<Object> getValueById(Model model, @PathVariable Integer id) {
        LOG.debug("getValueById() {}", id);
        return new ResponseEntity<Object>(valueOfSensorService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Value by id " + id + " not found")), HttpStatus.OK);
    }

    @PostMapping(value = "/values", consumes = "application/json", produces = "application/json")
    ResponseEntity<Object> createValue(Model model, @RequestBody @Valid ValueOfSensors value) {
        LOG.debug("createValue() {}", value);

        Person person = personService.findByEmail(value.getPerson().getUserEmail()).get();
        Shift shift = shiftService.findByName(value.getShift().getShiftName()).get();
        Sensor sensor = sensorService.findByName(value.getSensor().getSensorName()).get();

        value.setPerson(person);
        value.setShift(shift);
        value.setSensor(sensor);

        valueOfSensorService.createByIds(value);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/values", consumes = "application/json", produces = "application/json")
    ResponseEntity<Object> updateValue(Model model, @RequestBody @Valid ValueOfSensors value) {
        LOG.debug("updateValue() {}", value);

        Shift shift = shiftService.findByName(value.getShift().getShiftName()).get();
        Sensor sensor = sensorService.findByName(value.getSensor().getSensorName()).get();

        value.setShift(shift);
        value.setSensor(sensor);

        valueOfSensorService.updateByIds(value);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/values/{id}")
    ResponseEntity<Object> deleteValue(Model model, @PathVariable Integer id) {
        LOG.debug("deleteValue() {}", id);
        valueOfSensorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/values/filter")
    List<ValueOfSensors> getValues(Model model, @RequestParam("sensor") String sensor) {
        LOG.debug("getValues()");
        Sensor foundSensor = sensorService.findByName(sensor).get();
        return valueOfSensorService.findAllBySensor(foundSensor);
    }
}
