package com.foxrider.dao;

import com.foxrider.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer> {
    Optional<Shift> getShiftByShiftName(String name);
}
