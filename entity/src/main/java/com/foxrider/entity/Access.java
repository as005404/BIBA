package com.foxrider.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ACCESS")
public class Access {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCESS_ID")
    private Integer accessId;
    @Column(name = "ACCESS_NAME")
    private String accessName;

    public Access() {
    }

    public Access(String accessName) {
        this.accessName = accessName;
    }

    public Integer getAccessId() {
        return accessId;
    }

    public void setAccessId(Integer accessId) {
        this.accessId = accessId;
    }

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Access access = (Access) o;
        return Objects.equals(accessId, access.accessId) && Objects.equals(accessName, access.accessName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessId, accessName);
    }

    @Override
    public String toString() {
        return "Access{" +
                "accessId=" + accessId +
                ", accessName='" + accessName + '\'' +
                '}';
    }
}
