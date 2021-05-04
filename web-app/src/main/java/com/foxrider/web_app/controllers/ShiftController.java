package com.foxrider.web_app.controllers;

import com.foxrider.entity.Shift;
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
public class ShiftController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiftController.class);
    private final ValueOfSensorRestClient valueOfSensorRestClient;
    private final SensorRestClient sensorRestClient;
    private final ShiftRestClient shiftRestClient;
    private final UtilRestClient utilRestClient;
    private final PersonRestClient personRestClient;

    @Autowired
    public ShiftController(ValueOfSensorRestClient valueOfSensorRestClient, SensorRestClient sensorRestClient, ShiftRestClient shiftRestClient, UtilRestClient utilRestClient, PersonRestClient personRestClient) {
        this.valueOfSensorRestClient = valueOfSensorRestClient;
        this.sensorRestClient = sensorRestClient;
        this.shiftRestClient = shiftRestClient;
        this.utilRestClient = utilRestClient;
        this.personRestClient = personRestClient;
    }

    @GetMapping("/shifts")
    public String getShifts(Model model,
                            @CookieValue(value = "jwt-cookie", defaultValue = "null") String jwtCookie) {
        LOGGER.debug("getShifts()");
        if (jwtCookie.equalsIgnoreCase("null"))
            return "redirect:/login";

        model.addAttribute("shifts", shiftRestClient.findAll(jwtCookie));
        return "shifts";
    }

    @GetMapping(value = "/shifts/{id}")
    public String getFormOfEditShifts(@PathVariable String id,
                                      Model model,
                                      @CookieValue(value = "jwt-cookie", defaultValue = "null") String jwtCookie) {
        LOGGER.debug("getFormOfEditShifts() {} {}", id, model);

        Shift shift = shiftRestClient.findByName(id, jwtCookie).get();
        model.addAttribute("shift", shift);
        model.addAttribute("isEdit", true);
        return "shift";

    }

    @GetMapping(value = "/shifts/add")
    public String getFormOfaddShift(Model model, @CookieValue(value = "jwt-cookie", defaultValue = "null") String jwtCookie) {
        model.addAttribute("shift", new Shift());
        model.addAttribute("isEdit", false);
        return "shift";
    }


    @GetMapping(value = "shifts/{id}/delete")
    public String deleteShift(@PathVariable Integer id,
                              @CookieValue(value = "jwt-cookie", defaultValue = "null") String jwtCookie) {
        LOGGER.debug("deleteShift() id={}", id);
        shiftRestClient.delete(id, jwtCookie);
        return "redirect:/shifts";
    }

    @PostMapping(value = "shifts/add")
    public String addShift(Shift shift, @CookieValue(value = "jwt-cookie", defaultValue = "null") String jwtCookie) {
        LOGGER.debug("addShift {}", shift);
        shiftRestClient.create(shift, jwtCookie);
        return "redirect:/shifts";
    }

    @PostMapping(value = "shifts/{id}")
    public String editShift(Shift shift,
                            @CookieValue(value = "jwt-cookie", defaultValue = "null") String jwtCookie,
                            @PathVariable("id") String id) {
        LOGGER.debug("editShift() {}", shift);
        shiftRestClient.update(shift, jwtCookie);
        return "redirect:/shifts";
    }

}
