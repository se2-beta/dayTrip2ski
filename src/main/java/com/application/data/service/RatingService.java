package com.application.data.service;

import com.application.data.entity.Rating;
import com.application.data.entity.SkiResort;
import com.application.data.entity.User;
import com.application.data.restpojo.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    private final RatingRepository repository;
    private final SkiResortRepository skiResortRepository;
    private final DistanceService service;

    @Autowired
    public RatingService(RatingRepository repository, DistanceService service, SkiResortRepository skiResortRepository) {
        this.service = service;
        this.repository = repository;
        this.skiResortRepository = skiResortRepository;
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

    public void setRating(User user, SkiResort skiResort, Double rating, Double distanceVal, Double durationVal) {
        Rating ratingObj = new Rating(user, skiResort, rating, distanceVal, durationVal);
        repository.save(ratingObj);
    }

    public List<Rating> getAllRating() {
        return repository.findAll();
    }

    public void setDistDur(User user, SkiResort skiResort) {
        Optional<Rating> optionalRating = get(user, skiResort);
        Rating rating;
        if (optionalRating.isEmpty()) {
            rating = new Rating(user, skiResort);
            repository.save(rating);
        } else {
            rating = optionalRating.get();
        }
        distanceApiCall(rating, skiResort, user);
        repository.save(rating);
    }

    public void calculateRating(User user, SkiResort skiResort) {
        Optional<Rating> optionalRating = get(user, skiResort);
        Rating rating;
        if (optionalRating.isPresent()) {
            rating = optionalRating.get();
        } else {
            rating = new Rating(user, skiResort);
            repository.save(rating);
        }
        rating.setRating(calculate(rating, user, skiResort));
        repository.save(rating);
    }

    public void calculateAllRating() {
        List<Rating> ratings = getAllRating();
        for (Rating r : ratings) {
            calculateRating(r.getUser(), r.getSkiResort());
        }
    }

    public Rating getFrontend(User user, SkiResort skiResort) {
        Optional<Rating> optionalRating = get(user, skiResort);
        Rating rating;
        if (optionalRating.isEmpty()) {
            rating = new Rating(user, skiResort);
            distanceApiCall(rating, skiResort, user);
            rating.setRating(calculate(rating, user, skiResort));
            repository.save(rating);
        } else {
            rating = optionalRating.get();
        }
        return rating;
    }

    private void distanceApiCall(Rating rating, SkiResort skiResort, User user) {
        String olon, olat, dlat, dlon;
        olon = String.valueOf(user.getHomeLon());
        olat = String.valueOf(user.getHomeLat());
        dlat = String.valueOf(skiResort.getPosLat());
        dlon = String.valueOf(skiResort.getPosLon());

        Element element = service.getDistDur(olat, olon, dlat, dlon);
        rating.setDistanceVal(Double.valueOf(element.getDistance().getValue()));
        rating.setDurationVal(Double.valueOf(element.getDuration().getValue()));
    }

    private double calculate(Rating rating, User user, SkiResort skiResort) {
        double durmax = repository.findFirstByUserOrderByDurationValDesc(user).get().getDurationVal();
        double ratingcalc = 1 - (rating.getDurationVal())/(durmax);
        double all = (user.getWeightFreshSnow() - 1)+(user.getWeightOccupancy() - 1)+(user.getWeightSlopeLength() - 1)+(user.getWeightTravelTime() - 1);
        return ((user.getWeightFreshSnow() - 1) * skiResort.getAmountFreshSnow()/(double)(skiResortRepository.findFirstByOrderByAmountFreshSnowDesc().get().getAmountFreshSnow())
                + ((user.getWeightOccupancy() - 1) * skiResort.getCurrentUtilizationPercent())/(double)(skiResortRepository.findFirstByOrderByCurrentUtilizationPercentDesc().get().getCurrentUtilizationPercent())
                + ((user.getWeightSlopeLength() - 1) * skiResort.getTotalLength())/(skiResortRepository.findFirstByOrderByTotalLengthDesc().get().getTotalLength())
                + ((user.getWeightTravelTime() - 1) * ratingcalc))
                *100/all;

    }
}
