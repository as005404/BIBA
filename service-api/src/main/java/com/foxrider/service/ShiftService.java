package com.foxrider.service;

import com.foxrider.entity.Shift;

import java.util.List;
import java.util.Optional;

public interface ShiftService {

    List<Shift> findAll();

    Optional<Shift> findById(Integer shiftId);

    Shift create(Shift shift);

    Shift update(Shift shift);

    void delete(Integer shiftId);

}
