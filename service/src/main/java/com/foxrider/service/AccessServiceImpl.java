package com.foxrider.service;

import com.foxrider.dao.AccessRepository;
import com.foxrider.entity.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AccessServiceImpl implements AccessService{


    AccessRepository accessRepository;

    @Autowired
    public AccessServiceImpl(AccessRepository accessRepository) {
        this.accessRepository = accessRepository;
    }

    @Override
    public List<Access> findAll() {
        return accessRepository.findAll();
    }

    @Override
    public Optional<Access> findById(Integer accessId) {
        return accessRepository.findById(accessId);
    }

    @Override
    public Access create(Access access) {
        return accessRepository.save(access);
    }

    @Override
    public Access update(Access access) {
        return accessRepository.save(access);
    }

    @Override
    public void delete(Integer accessId) {
        accessRepository.delete(findById(accessId)
                .orElseThrow(() -> new EntityNotFoundException("Access entity not found")));

    }
}
