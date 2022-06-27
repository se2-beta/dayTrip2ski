package com.application.data.service;

import com.application.data.entity.Rating;
import com.application.data.entity.SkiResort;
import com.application.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    Optional<Rating> findByUserAndSkiResort(User user, SkiResort skiResort);
}
