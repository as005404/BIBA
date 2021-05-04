package com.foxrider.dao;

import com.foxrider.entity.Access;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessRepository extends JpaRepository<Access, Integer> {
    Optional<Access> getAccessByAccessName(String name);
}
