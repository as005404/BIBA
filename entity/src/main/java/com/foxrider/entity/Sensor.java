package com.foxrider.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "SENSOR")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SENSOR_ID")
    private Integer sensorId;

    @Column(name = "SENSOR_NAME")
    @NotBlank(message = "Sensor name is mandatory")
    @Size(max = 30, message = "Sensor name should be b-n 1 and 30 characters")
    private String sensorName;

    @Column(name = "SENSOR_DESC")
    @NotBlank(message = "Sensor description is mandatory")
    @Size(max = 30, message = "Sensor description should be b-n 1 and 100 characters")
    private String sensorDesc;

    @Column(name = "SENSOR_UNIT")
    @NotBlank(message = "Sensor unit description is mandatory")
    @Size(max = 10, message = "Sensor unit should be b-n 1 and 10 characters")
    private String sensorUnit;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sensor", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ValueOfSensors> values;

    public Sensor() {
    }

    public Sensor(String sensorName) {
        this.sensorName = sensorName;
    }

    public Sensor(String sensorName, String sensorDesc, String sensorUnit) {
        this.sensorName = sensorName;
        this.sensorDesc = sensorDesc;
        this.sensorUnit = sensorUnit;
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
