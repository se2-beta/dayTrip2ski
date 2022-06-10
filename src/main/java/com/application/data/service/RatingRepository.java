package com.application.data.service;

import com.application.data.entity.Rating;
import com.application.data.entity.SkiResort;
import com.application.data.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RatingRepository extends JpaRepository<Rating, Integer>{
    Optional<Rating> findByUserAndSkiResort(User user, SkiResort skiResort);
//    Page<Rating> findAllByUser(Pageable pageable);
//    public Optional<Rating> findByUserAndSkiResort(UUID userUUID, UUID skiResortUUID);
}
