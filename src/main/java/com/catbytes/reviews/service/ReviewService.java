package com.catbytes.reviews.service;

import com.catbytes.reviews.entity.Review;

public interface ReviewService {

    // Method for getting feedback by id
    Review getReviewById(Long id);

    Long submitReview(Review review);

}
