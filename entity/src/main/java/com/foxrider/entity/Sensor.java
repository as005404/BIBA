package com.foxrider.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SENSOR")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SENSOR_ID")
    private Integer sensorId;
    @Column(name = "SENSOR_NAME")
    private String sensorName;
    @Column(name = "SENSOR_DESC")
    private String sensorDesc;
    @Column(name = "SENSOR_UNIT")
    private String sensorUnit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SENSOR_ID", nullable = false, insertable = false, updatable = false)
    private ValueOfSensors value;

    public Sensor() {
    }

    public Sensor(String sensorName, String sensorDesc, String sensorUnit, ValueOfSensors value) {
        this.sensorName = sensorName;
        this.sensorDesc = sensorDesc;
        this.sensorUnit = sensorUnit;
        this.value = value;
    }

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getSensorDesc() {
        return sensorDesc;
    }

    public void setSensorDesc(String sensorDesc) {
        this.sensorDesc = sensorDesc;
    }

    public String getSensorUnit() {
        return sensorUnit;
    }

    public void setSensorUnit(String sensorUnit) {
        this.sensorUnit = sensorUnit;
    }

    public ValueOfSensors getValue() {
        return value;
    }

    public void setValue(ValueOfSensors value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return Objects.equals(sensorId, sensor.sensorId) && Objects.equals(sensorName, sensor.sensorName) && Objects.equals(sensorDesc, sensor.sensorDesc) && Objects.equals(sensorUnit, sensor.sensorUnit) && Objects.equals(value, sensor.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensorId, sensorName, sensorDesc, sensorUnit, value);
    }
}
