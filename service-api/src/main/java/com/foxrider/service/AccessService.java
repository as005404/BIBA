package com.foxrider.service;

import com.foxrider.entity.Access;

import java.util.List;
import java.util.Optional;

public interface AccessService {
    List<Access> findAll();

    Optional<Access> findById(Integer accessId);

    Optional<Access> findByName(String name);

    Access create(Access access);

    Access update(Access access);

    void delete(Integer accessId);
}
