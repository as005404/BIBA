package com.foxrider.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "SHIFT")
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SHIFT_ID")
    private Integer shiftId;

    @Column(name = "SHIFT_NAME")
    @NotBlank(message = "Shift name is mandatory")
    @Size(max = 30, message = "Shift name should be b-n 1 and 30 characters")
    private String shiftName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shift", cascade = CascadeType.ALL)
    @JsonIgnore
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
