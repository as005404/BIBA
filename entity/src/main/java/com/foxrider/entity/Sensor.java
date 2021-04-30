package com.foxrider.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sensor")
    @JsonBackReference
    private Set<ValueOfSensors> values;

    public Sensor() {
    }

    public Sensor(String sensorName, String sensorDesc, String sensorUnit, Set<ValueOfSensors> values) {
        this.sensorName = sensorName;
        this.sensorDesc = sensorDesc;
        this.sensorUnit = sensorUnit;
        this.values = values;
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

    public Set<ValueOfSensors> getValues() {
        return values;
    }

    public void setValues(Set<ValueOfSensors> values) {
        this.values = values;
    }

}
