package com.foxrider.service;

import com.foxrider.entity.Sensor;
import com.foxrider.entity.ValueOfSensors;

import java.util.List;
import java.util.Optional;

public interface ValueOfSensorService {
    List<ValueOfSensors> findAll();

    List<ValueOfSensors> findAllBySensor(Sensor sensor);

    Optional<ValueOfSensors> findById(Integer id);

    ValueOfSensors create(ValueOfSensors value);

    void createByIds(ValueOfSensors value);

    ValueOfSensors update(ValueOfSensors value);

    void updateByIds(ValueOfSensors value);

    void delete(Integer id);
}
