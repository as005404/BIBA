package com.foxrider.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "SHIFT")
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SHIFT_ID")
    private Integer shiftId;

    @Column(name = "SHIFT_NAME")
    private String shiftName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shift")
    @JsonBackReference
    private Set<ValueOfSensors> values;

    public Shift() {
    }

    public Shift(String shiftName, Set<ValueOfSensors> values) {
        this.shiftName = shiftName;
        this.values = values;
    }

    public Shift(String shiftName) {
        this.shiftName = shiftName;
    }

    public Integer getShiftId() {
        return shiftId;
    }

    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public Set<ValueOfSensors> getValues() {
        return values;
    }

    public void setValues(Set<ValueOfSensors> values) {
        this.values = values;
    }

}
