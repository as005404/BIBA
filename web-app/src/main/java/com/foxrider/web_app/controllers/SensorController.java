package com.foxrider.web_app.controllers;

import com.foxrider.entity.Sensor;
import com.foxrider.rest_client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SensorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorController.class);
    private final ValueOfSensorRestClient valueOfSensorRestClient;
    private final SensorRestClient sensorRestClient;
    private final ShiftRestClient shiftRestClient;
    private final UtilRestClient utilRestClient;
    private final PersonRestClient personRestClient;

    @Autowired
    public SensorController(ValueOfSensorRestClient valueOfSensorRestClient, SensorRestClient sensorRestClient, ShiftRestClient shiftRestClient, UtilRestClient utilRestClient, PersonRestClient personRestClient) {
        this.valueOfSensorRestClient = valueOfSensorRestClient;
        this.sensorRestClient = sensorRestClient;
        this.shiftRestClient = shiftRestClient;
        this.utilRestClient = utilRestClient;
        this.personRestClient = personRestClient;
    }

    @GetMapping("/sensors")
    public String getSensors(Model model,
                             @CookieValue(value = "jwt-cookie", defaultValue = "null") String jwtCookie) {
        LOGGER.debug("getSensors()");

        model.addAttribute("sensors", sensorRestClient.findAll(jwtCookie));
        return "sensors";
    }

    @GetMapping(value = "/sensors/{id}")
    public String getFormOfEditSensors(@PathVariable String id,
                                       Model model,
                                       @CookieValue(value = "jwt-cookie", defaultValue = "null") String jwtCookie) {
        LOGGER.debug("getFormOfEditSensors() {} {}", id, model);
        Sensor sensor = sensorRestClient.findByName(id, jwtCookie).get();
        model.addAttribute("sensor", sensor);
        return "sensor";

    }

    @GetMapping(value = "/sensors/add")
    public String getFormOfaddSensor(Model model, @CookieValue(value = "jwt-cookie", defaultValue = "null") String jwtCookie) {
        LOGGER.debug("getFormOfaddSensor() {}", model);
        model.addAttribute("sensor", new Sensor());
        return "sensor";
    }


    @GetMapping(value = "sensors/{id}/delete")
    public String deleteSensor(@PathVariable Integer id,
                               @CookieValue(value = "jwt-cookie", defaultValue = "null") String jwtCookie) {
        LOGGER.debug("deleteSensor() id={}", id);
        sensorRestClient.delete(id, jwtCookie);
        return "redirect:/sensors";
    }

    @PostMapping(value = "sensors/add")
    public String addSensor(Sensor sensor, @CookieValue(value = "jwt-cookie", defaultValue = "null") String jwtCookie) {
        LOGGER.debug("addSensor {}", sensor);
        sensorRestClient.create(sensor, jwtCookie);
        return "redirect:/sensors";
    }

    @PostMapping(value = "sensors/{id}")
    public String editSensor(Sensor sensor,
                             @CookieValue(value = "jwt-cookie", defaultValue = "null") String jwtCookie,
                             @PathVariable("id") String id) {
        LOGGER.debug("editSensor() {}", sensor);
        sensorRestClient.update(sensor, jwtCookie);
        return "redirect:/sensors";
    }


}
