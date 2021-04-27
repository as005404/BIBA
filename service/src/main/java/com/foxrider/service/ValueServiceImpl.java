package com.foxrider.service;

import com.foxrider.dao.ValueOfSensorRepository;
import com.foxrider.entity.ValueOfSensors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ValueServiceImpl implements ValueOfSensorService {
    ValueOfSensorRepository repository;

    @Autowired
    public ValueServiceImpl(ValueOfSensorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ValueOfSensors> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<ValueOfSensors> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public ValueOfSensors create(ValueOfSensors value) {
        return repository.save(value);
    }

    @Override
    public ValueOfSensors update(ValueOfSensors value) {
        return repository.save(value);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(findById(id).orElseThrow(() -> new EntityNotFoundException("ValueOfSensor entity not found")));
    }
}
