package com.example.blog.service;

import com.example.blog.exceptions.RatingServiceException;
import com.example.blog.model.Rating;
import com.example.blog.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;




@Slf4j
@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    public Rating save(Rating rating) {
        if (rating.getPost() == null) {
            throw new RatingServiceException("Post is required for saving a rating");
        }
        log.info("Rating: {}", rating.getPost().getId());
        return ratingRepository.save(rating);
    }
}