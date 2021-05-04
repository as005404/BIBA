package com.foxrider.dao;

import com.foxrider.entity.Sensor;
import com.foxrider.entity.ValueOfSensors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ValueOfSensorRepository extends JpaRepository<ValueOfSensors, Integer> {

    @Modifying
    @Query(
            value = "INSERT INTO VALUE_OF_SENSOR(USER_ID, SENSOR_ID, SHIFT_ID, VALUE, DATETIME) " +
                    "VALUES (:pid, :sid, :shid, :val, :dt)",
            nativeQuery = true)
    void saveByIds(@Param("pid") Integer personId, @Param("sid") Integer sensorId, @Param("shid") Integer shiftId, @Param("val") Double value, @Param("dt") LocalDateTime dateTime);


    @Modifying
    @Query(
            value = "UPDATE VALUE_OF_SENSOR SET USER_ID=:pid, SENSOR_ID=:sid, SHIFT_ID=:shid, VALUE=:val WHERE ID=:id",
            nativeQuery = true)
    void updateByIds(@Param("pid") Integer personId, @Param("sid") Integer sensorId, @Param("shid") Integer shiftId, @Param("val") Double value, @Param("id") Integer id);

    @Modifying
    @Query(
            value = "DELETE FROM VALUE_OF_SENSOR  WHERE ID=:id",
            nativeQuery = true)
    void deleteById(@Param("id") Integer id);

    List<ValueOfSensors> getAllBySensor(Sensor sensor);
}
