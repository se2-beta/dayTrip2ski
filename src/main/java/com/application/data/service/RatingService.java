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
    private final DistanceService service;

    @Autowired
    public RatingService(RatingRepository repository, DistanceService service) {
        this.service = service;
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

    public List<Rating> getAllRating() {
        return repository.findAll();
    }

    public void setDistDur(User user, SkiResort skiResort) {
        Optional<Rating> optionalRating = get(user, skiResort);
        Rating rating;
        if (!optionalRating.isPresent()) {
            rating = new Rating(user, skiResort);
            repository.save(rating);
        } else {
            rating = optionalRating.get();
        }
        String olon, olat, dlat, dlon;
        olon = String.valueOf(user.getHomeLon());
        olat = String.valueOf(user.getHomeLat());
        dlat = String.valueOf(skiResort.getPosLat());
        dlon = String.valueOf(skiResort.getPosLon());


        Element element = service.getDistDur(olat, olon, dlat, dlon);
        rating.setDistanceStr(element.getDistance().getText());
        rating.setDistanceVal(Double.valueOf(element.getDistance().getValue()));
        rating.setDurationStr(element.getDuration().getText());
        rating.setDurationVal(Double.valueOf(element.getDuration().getValue()));

        repository.save(rating);
    }

    public double calculateRating(User user, SkiResort skiResort) {
        Optional<Rating> optionalRating = get(user, skiResort);
        Rating rating;
        if (!optionalRating.isPresent()) {
            rating = new Rating(user, skiResort);
            repository.save(rating);
        } else {
            rating = optionalRating.get();
        }
        double r = user.getWeightFreshSnow() * skiResort.getAmountFreshSnow() + user.getWeightOccupancy() * skiResort.getCurrentUtilizationPercent() +
                user.getWeightSlopeLength() * skiResort.getTotalLength() + user.getWeightTravelTime() * rating.getDurationVal();
        rating.setRating(r);
        repository.save(rating);
        return r;
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
            String olon, olat, dlat, dlon;
            olon = String.valueOf(user.getHomeLon());
            olat = String.valueOf(user.getHomeLat());
            dlat = String.valueOf(skiResort.getPosLat());
            dlon = String.valueOf(skiResort.getPosLon());

            Element element = service.getDistDur(olat, olon, dlat, dlon);
            rating.setDistanceStr(element.getDistance().getText());
            rating.setDistanceVal(Double.valueOf(element.getDistance().getValue()));
            rating.setDurationStr(element.getDuration().getText());
            rating.setDurationVal(Double.valueOf(element.getDuration().getValue()));

            double r = user.getWeightFreshSnow() * skiResort.getAmountFreshSnow() + user.getWeightOccupancy() * skiResort.getCurrentUtilizationPercent() +
                    user.getWeightSlopeLength() * skiResort.getTotalLength() - user.getWeightTravelTime() * rating.getDurationVal() / 100;
            rating.setRating(r);
            repository.save(rating);
        } else {
            rating = optionalRating.get();
        }
        return rating;
    }

    public void setDistDur(Rating rating) {

    }
}
