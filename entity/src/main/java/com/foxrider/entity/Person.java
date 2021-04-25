package com.foxrider.entity;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PERSON")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    @Column(name = "USER_PWD_HASH")
    private String userPasswordHash;

    @Column(name = "USER_SALT")
    private String userSalt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PERSON_ACCESS",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ACCESS_ID")
    )
    private Set<Access> roles;


    public Person() {
    }

    public Person(String userEmail, String userPasswordHash, String userSalt, Set<Access> roles) {
        this.userEmail = userEmail;
        this.userPasswordHash = userPasswordHash;
        this.userSalt = userSalt;
        this.roles = roles;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(userId, person.userId) && Objects.equals(userEmail, person.userEmail) && Objects.equals(userPasswordHash, person.userPasswordHash) && Objects.equals(userSalt, person.userSalt) && Objects.equals(roles, person.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userEmail, userPasswordHash, userSalt, roles);
    }
}
