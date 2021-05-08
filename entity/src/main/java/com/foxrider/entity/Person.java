package com.foxrider.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "PERSON")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer userId;

    @NotBlank(message = "Email is mandatory")
    @Size(min = 2, message = "Email should be b-n 2 and 30 characters", max = 30)
    @Column(name = "USER_EMAIL")
    private String userEmail;

    @Column(name = "USER_PWD_HASH")
    @Size(min = 2, message = "Password should be b-n 2 and 512 characters", max = 512)
    private String userPasswordHash;

    @Column(name = "USER_SALT")
    private String userSalt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PERSON_ACCESS",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ACCESS_ID")
    )
    private Set<Access> roles;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ValueOfSensors> values;


    public Person() {
    }

    public Person(String userEmail) {
        this.userEmail = userEmail;
    }

    public Person(String userEmail, String userPasswordHash, String userSalt, Set<Access> roles) {
        this.userEmail = userEmail;
        this.userPasswordHash = userPasswordHash;
        this.userSalt = userSalt;
        this.roles = roles;
    }

    public Person(Integer userId, String userEmail, String userPasswordHash, String userSalt, Set<Access> roles, Set<ValueOfSensors> values) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userPasswordHash = userPasswordHash;
        this.userSalt = userSalt;
        this.roles = roles;
        this.values = values;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPasswordHash() {
        return userPasswordHash;
    }

    public void setUserPasswordHash(String userPasswordHash) {
        this.userPasswordHash = userPasswordHash;
    }

    public String getUserSalt() {
        return userSalt;
    }

    public void setUserSalt(String userSalt) {
        this.userSalt = userSalt;
    }

    public Set<Access> getRoles() {
        return roles;
    }

    public void setRoles(Set<Access> roles) {
        this.roles = roles;
    }

    public Set<ValueOfSensors> getValues() {
        return values;
    }

    public void setValues(Set<ValueOfSensors> values) {
        this.values = values;
    }
}
