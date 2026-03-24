package com.groundbnb.controller;

import com.groundbnb.entity.Review;
import com.groundbnb.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public Review create(@RequestBody Review review) {
        return reviewService.create(review);
    }

    @GetMapping("/listing/{id}")
    public List<Review> getByListing(@PathVariable Long id) {
        return reviewService.getByListingId(id);
    }
}
