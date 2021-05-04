package com.foxrider.service;

import com.foxrider.dao.ValueOfSensorRepository;
import com.foxrider.entity.Sensor;
import com.foxrider.entity.ValueOfSensors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<ValueOfSensors> findAllBySensor(Sensor sensor) {
        return repository.getAllBySensor(sensor);
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
    public void createByIds(ValueOfSensors value) {
        repository.saveByIds(
                value.getPerson().getUserId(),
                value.getSensor().getSensorId(),
                value.getShift().getShiftId(),
                value.getValue(),
                value.getDateTime()
        );
    }

    @Override
    public ValueOfSensors update(ValueOfSensors value) {
        return repository.save(value);
    }

    @Override
    public void updateByIds(ValueOfSensors value) {
        repository.updateByIds(
                value.getPerson().getUserId(),
                value.getSensor().getSensorId(),
                value.getShift().getShiftId(),
                value.getValue(),
                value.getId()
        );
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
