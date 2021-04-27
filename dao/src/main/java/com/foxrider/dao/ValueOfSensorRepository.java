package com.foxrider.dao;

import com.foxrider.entity.ValueOfSensors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValueOfSensorRepository extends JpaRepository<ValueOfSensors, Integer> {
}
