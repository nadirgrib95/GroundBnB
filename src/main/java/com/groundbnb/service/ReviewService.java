package com.groundbnb.service;

import com.groundbnb.entity.Review;
import com.groundbnb.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService (ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review create(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getByListingId(Long listingId) {
        return reviewRepository.findByListingId(listingId);
    }
}
