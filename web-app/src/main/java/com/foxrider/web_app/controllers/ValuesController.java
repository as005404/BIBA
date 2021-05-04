package com.foxrider.web_app.controllers;

import com.foxrider.entity.Person;
import com.foxrider.entity.ValueOfSensors;
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

import java.time.LocalDateTime;

@Controller
public class ValuesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValuesController.class);
    private final ValueOfSensorRestClient valueOfSensorRestClient;
    private final SensorRestClient sensorRestClient;
    private final ShiftRestClient shiftRestClient;
    private final UtilRestClient utilRestClient;
    private final PersonRestClient personRestClient;

    @Autowired
    public ValuesController(ValueOfSensorRestClient valueOfSensorRestClient, SensorRestClient sensorRestClient, ShiftRestClient shiftRestClient, UtilRestClient utilRestClient, PersonRestClient personRestClient) {
        this.valueOfSensorRestClient = valueOfSensorRestClient;
        this.sensorRestClient = sensorRestClient;
        this.shiftRestClient = shiftRestClient;
        this.utilRestClient = utilRestClient;
        this.personRestClient = personRestClient;
    }

    @GetMapping("/values")
    public String getValues(Model model, @CookieValue(value = "jwt-cookie", defaultValue = "null") String jwtCookie) {
        LOGGER.debug("getValues()");
        if (jwtCookie.equalsIgnoreCase("null"))
            return "redirect:/login";
        model.addAttribute("sensors", sensorRestClient.findAll(jwtCookie));
        model.addAttribute("values", valueOfSensorRestClient.findAll(jwtCookie));
        model.addAttribute("isAdmin", utilRestClient.getRoles(jwtCookie).contains("ROLE_ADMIN"));
        return "values";
    }

    @GetMapping(value = "/values/{id}")
    public String getFormOfEditValues(@PathVariable Integer id,
                                      Model model,
                                      @CookieValue(value = "jwt-cookie", defaultValue = "null") String jwtCookie) {
        LOGGER.debug("getFormOfEditBlog() {} {}", id, model);
        ValueOfSensors value = valueOfSensorRestClient.findById(id, jwtCookie).get();

        model.addAttribute("value", value);
        model.addAttribute("username", value.getPerson().getUserEmail());
        model.addAttribute("shifts", shiftRestClient.findAll(jwtCookie));
        model.addAttribute("sensors", sensorRestClient.findAll(jwtCookie));
        model.addAttribute("isEdit", true);
        return "value";

    }

    @GetMapping(value = "/values/add")
    public String getFormOfaddValue(Model model, @CookieValue(value = "jwt-cookie", defaultValue = "null") String jwtCookie) {
        model.addAttribute("value", new ValueOfSensors());
        model.addAttribute("username", utilRestClient.getUsername(jwtCookie));
        model.addAttribute("shifts", shiftRestClient.findAll(jwtCookie));
        model.addAttribute("sensors", sensorRestClient.findAll(jwtCookie));
        model.addAttribute("isEdit", false);

        return "value";
    }


    @GetMapping(value = "values/{id}/delete")
    public String deleteValue(@PathVariable Integer id,
                              @CookieValue(value = "jwt-cookie", defaultValue = "null") String jwtCookie) {
        LOGGER.debug("deleteBlog() id={}", id);
        valueOfSensorRestClient.delete(id, jwtCookie);
        return "redirect:/values";
    }

    @PostMapping(value = "values/add")
    public String addValue(ValueOfSensors value, @CookieValue(value = "jwt-cookie", defaultValue = "null") String jwtCookie) {
        // TODO: все это перенести в REST используя здесь имена, а там по именам искать индексы элементов а затем в секюрити конфиге сделать shift, sensor, person admin and moder only
        // TODO: moder не может давать роли
        Person person = new Person(utilRestClient.getUsername(jwtCookie));

        value.setPerson(person);
        value.setDateTime(LocalDateTime.now());

        valueOfSensorRestClient.create(value, jwtCookie);
        return "redirect:/values";
    }

    @PostMapping(value = "values/{id}")
    public String editValue(ValueOfSensors value,
                            @CookieValue(value = "jwt-cookie", defaultValue = "null") String jwtCookie,
                            @PathVariable("id") Integer id) {
        LOGGER.debug("editValue() {}", value);

        ValueOfSensors valueOfSensors = valueOfSensorRestClient.findById(id, jwtCookie).get();
        value.setPerson(valueOfSensors.getPerson());

        valueOfSensorRestClient.update(value, jwtCookie);
        return "redirect:/values";
    }

}
