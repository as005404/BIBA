package com.foxrider.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ACCESS")
public class Access {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCESS_ID")
    private Integer accessId;

    @Column(name = "ACCESS_NAME")
    private String accessName;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    private List<Person> users;

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

    public List<Person> getUsers() {
        return users;
    }

    public void setUsers(List<Person> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Access{" +
                "accessId=" + accessId +
                ", accessName='" + accessName + '\'' +
                ", users=" + users +
                '}';
    }
}
