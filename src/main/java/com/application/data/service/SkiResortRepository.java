package com.application.data.service;

import com.application.data.entity.SkiResort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SkiResortRepository extends JpaRepository<SkiResort, UUID> {
    Optional<SkiResort> findSkiResortByName(String name);
}
