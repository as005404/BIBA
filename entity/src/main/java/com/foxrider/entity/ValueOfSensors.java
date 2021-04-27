package com.foxrider.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "VALUE_OF_SENSOR")
public class ValueOfSensors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "value")
    private Set<Person> people;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "value")
    private Set<Shift> shifts;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "value")
    private Set<Sensor> sensors;

    @Column(name = "VALUE")
    private Double value;

    @Column(name = "DATETIME")
    private LocalDateTime dateTime;

    public ValueOfSensors() {
    }

    public ValueOfSensors(Set<Person> people, Set<Shift> shifts, Set<Sensor> sensors, Double value, LocalDateTime dateTime) {
        this.people = people;
        this.shifts = shifts;
        this.sensors = sensors;
        this.value = value;
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public Set<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(Set<Shift> shifts) {
        this.shifts = shifts;
    }

    public Set<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(Set<Sensor> sensors) {
        this.sensors = sensors;
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
