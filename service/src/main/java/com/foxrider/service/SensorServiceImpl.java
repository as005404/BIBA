package com.foxrider.service;

import com.foxrider.dao.SensorRepository;
import com.foxrider.entity.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SensorServiceImpl implements SensorService {

    SensorRepository repository;

    @Autowired
    public SensorServiceImpl(SensorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Sensor> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Sensor> findById(Integer sensorId) {
        return repository.findById(sensorId);
    }

    @Override
    public Optional<Sensor> findByName(String name) {
        return repository.getSensorBySensorName(name);
    }

    @Override
    public Sensor create(Sensor sensor) {
        return repository.save(sensor);
    }

    @Override
    public Sensor update(Sensor sensor) {
        return repository.save(sensor);
    }

    @Override
    public void delete(Integer sensorId) {
        repository.delete(findById(sensorId).orElseThrow(() -> new EntityNotFoundException("Sensor entity not found")));
    }
}
