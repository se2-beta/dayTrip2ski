package com.application.data.service;

import com.application.data.entity.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RatingRepository extends JpaRepository<Rating, UUID>{
//    Page<Rating> findAllByUser(Pageable pageable);
//    public Optional<Rating> findByUserAndSkiResort(UUID userUUID, UUID skiResortUUID);
}
