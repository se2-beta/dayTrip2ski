package com.application.data.service;

import com.application.data.entity.SkiResort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SkiResortRepository extends JpaRepository<SkiResort, UUID> {
    Optional<SkiResort> findSkiResortByName(String name);

    @Query("select s from SkiResort s " +
            "where lower(s.name) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(s.region) like lower(concat('%', :searchTerm, '%'))")
    List<SkiResort> search(@Param("searchTerm") String searchTerm);
}
