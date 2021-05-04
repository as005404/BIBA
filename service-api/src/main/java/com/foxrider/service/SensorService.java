package com.foxrider.service;

import com.foxrider.entity.Sensor;

import java.util.List;
import java.util.Optional;

public interface SensorService {
    List<Sensor> findAll();

    Optional<Sensor> findById(Integer sensorId);

    Optional<Sensor> findByName(String name);

    Sensor create(Sensor sensor);

    Sensor update(Sensor sensor);

    void delete(Integer sensorId);
}
