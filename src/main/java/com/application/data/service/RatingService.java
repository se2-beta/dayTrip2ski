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

    public Optional<Rating> get(UUID id) {
        return repository.findById(id);
    }

    //    @Query "test"
    public Optional<Rating> get(User user, SkiResort skiResort) {
        return null;
    }

    public Optional<Rating> get(User user) {
        return null;
    }

//    public Page<Rating> list(Pageable pageable) {
//        return repository.findAllByUser(pageable);
//    }

    public void setRating(User user, SkiResort skiResort, double rating) {
        Rating ratingObj = new Rating(user, skiResort, rating);
        repository.save(ratingObj);
    }

//    public Optional<Rating> get(UUID userUUID, UUID skiResortUUID) {
//        return repository.findByUserAndSkiResort(userUUID, skiResortUUID);
//    }
}
