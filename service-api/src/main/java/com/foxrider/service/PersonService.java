package com.foxrider.service;

import com.foxrider.entity.Person;
import com.foxrider.entity.Shift;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<Person> findAll();

    Optional<Person> findById(Integer personId);

    Person create(Person person);

    Person update(Person person);

    void delete(Integer personId);
}