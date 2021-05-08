package com.foxrider.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "VALUE_OF_SENSOR")
public class ValueOfSensors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false)
    private Person person;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SHIFT_ID", nullable = false)
    private Shift shift;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SENSOR_ID", nullable = false)
    private Sensor sensor;

    @Column(name = "VALUE")
    private Double value;

    @Column(name = "DATETIME")
    private LocalDateTime dateTime;

    public ValueOfSensors() {
    }

    public ValueOfSensors(Person person, Shift shift, Sensor sensor, Double value, LocalDateTime dateTime) {
        this.person = person;
        this.shift = shift;
        this.sensor = sensor;
        this.value = value;
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
