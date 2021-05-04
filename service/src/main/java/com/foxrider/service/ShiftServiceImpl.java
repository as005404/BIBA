package com.foxrider.service;

import com.foxrider.dao.ShiftRepository;
import com.foxrider.entity.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShiftServiceImpl implements ShiftService {

    @Autowired
    ShiftRepository repository;


    @Override
    public List<Shift> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Shift> findById(Integer shiftId) {
        return repository.findById(shiftId);
    }

    @Override
    public Optional<Shift> findByName(String name) {
        return repository.getShiftByShiftName(name);
    }

    @Override
    public Shift create(Shift shift) {
        return repository.save(shift);
    }

    @Override
    public Shift update(Shift shift) {
        return repository.save(shift);
    }

    @Override
    public void delete(Integer shiftId) {
        repository.delete(findById(shiftId).orElseThrow(() -> new EntityNotFoundException("Shift entity not found")));
    }
}
