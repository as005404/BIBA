package com.foxrider;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "SHIFT")
public class Shift {
    @Id
    @Column(name = "SHIFT_ID")
    Integer shiftId;
    @Column(name = "SHIFT_NAME")
    String shiftName;

    public Shift() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shift shift = (Shift) o;
        return Objects.equals(shiftId, shift.shiftId) && Objects.equals(shiftName, shift.shiftName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shiftId, shiftName);
    }

    @Override
    public String toString() {
        return "Shift{" +
                "shiftId=" + shiftId +
                ", shiftName='" + shiftName + '\'' +
                '}';
    }
}
