package com.foxrider.service;

import com.foxrider.entity.Shift;

import java.util.List;
import java.util.Optional;

public interface ShiftService {

    List<Shift> findAll();

    Optional<Shift> findById(Integer shiftId);

    Shift create(Shift shiftId);

    Shift update(Shift shiftId);

    void delete(Integer shiftId);

}
