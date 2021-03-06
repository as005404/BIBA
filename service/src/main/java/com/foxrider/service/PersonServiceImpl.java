package com.foxrider.service;

import com.foxrider.dao.PersonRepository;
import com.foxrider.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    @Autowired
    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Person> findById(Integer personId) {
        return repository.findById(personId);
    }

    @Override
    public Optional<Person> findByEmail(String email) {
        return repository.getPersonByUserEmail(email);
    }

    @Override
    public Person create(Person person) {
        return repository.save(person);
    }

    @Override
    public Person update(Person person) {
        return repository.save(person);
    }

    @Override
    public void delete(Integer personId) {
        repository.delete(findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("Person entity not found")));
    }
}
