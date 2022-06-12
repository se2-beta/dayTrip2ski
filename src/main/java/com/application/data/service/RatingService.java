package com.application.data.service;

import com.application.data.entity.Rating;
import com.application.data.entity.SampleBook;
import com.application.data.entity.SkiResort;
import com.application.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RatingService {
    private final RatingRepository repository;

    @Autowired
    public RatingService(RatingRepository repository) {
        this.repository = repository;
    }

    public Optional<Rating> get(Integer id) {
        return repository.findById(id);
    }

    public Optional<Rating> get(User user, SkiResort skiResort) {
        return repository.findByUserAndSkiResort(user, skiResort);
    }

    public Optional<Rating> get(User user) {
        return null;
    }

    public void setRating(User user, SkiResort skiResort, Double rating, String distanceStr, Double distanceVal, String durationStr, Double durationVal) {
        Rating ratingObj = new Rating(user, skiResort, rating, distanceStr, distanceVal, durationStr, durationVal);
        repository.save(ratingObj);
    }
}
