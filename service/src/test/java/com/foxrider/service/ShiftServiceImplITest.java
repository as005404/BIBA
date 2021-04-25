package com.foxrider.service;

import com.foxrider.db.DataSourceConfigurer;
import com.foxrider.entity.Shift;
import com.foxrider.service.test_config.Config;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = {Config.class, DataSourceConfigurer.class})
@AutoConfigureTestDatabase(replace = NONE)
class ShiftServiceImplITest {

    private static final Logger LOG = LoggerFactory.getLogger(ShiftServiceImplITest.class);

    @Autowired
    ShiftService service;


    @Test
    void findAll() {
        LOG.debug("findAll()");
        List<Shift> shifts = service.findAll();
        assertNotNull(shifts);
        assertTrue(shifts.size() > 0);
    }

    @Test
    void findById() {
        LOG.debug("findById()");
        Optional<Shift> serviceById = service.findById(1);
        assertTrue(serviceById.isPresent());
        assertNotNull(serviceById.get());
    }

    @Test
    void create() {
        LOG.debug("create()");
        Shift shift = new Shift("Night");
        Shift shift1 = service.create(shift);
        List<Shift> afterAdding = service.findAll();
        assertNotNull(afterAdding);
        assertTrue(afterAdding.size() == 2);

    }

    @Test
    void update() {
        LOG.debug("update()");
        Shift shift = new Shift("Night");
        shift.setShiftId(1);

        service.update(shift);

        Optional<Shift> shiftAfterById = service.findById(1);
        assertTrue(shiftAfterById.isPresent());
        assertEquals(shiftAfterById.get(), shift);
    }

    @Test
    void delete() {
        LOG.debug("delete()");
        Shift shift = new Shift("Night");
        Shift shift1 = service.create(shift);
        List<Shift> afterAdding = service.findAll();
        assertNotNull(afterAdding);
        assertTrue(afterAdding.size() == 2);

        service.delete(shift1.getShiftId());
        List<Shift> afterDeleting = service.findAll();
        assertNotNull(afterDeleting);
        assertTrue(afterDeleting.size() == 1);
    }
}