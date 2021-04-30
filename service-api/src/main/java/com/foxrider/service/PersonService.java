package com.foxrider.service;

import com.foxrider.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<Person> findAll();

    Optional<Person> findById(Integer personId);

    Optional<Person> findByEmail(String email);

    Person create(Person person);

    Person update(Person person);

    void delete(Integer personId);
}
