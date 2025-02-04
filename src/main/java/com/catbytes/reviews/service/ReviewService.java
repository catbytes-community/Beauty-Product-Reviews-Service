package com.catbytes.reviews.service;

import com.catbytes.reviews.dto.DetailedReviewDTO;
import com.catbytes.reviews.dto.ReviewDTO;
import com.catbytes.reviews.entity.Review;

public interface ReviewService {

    // Method for getting feedback by id
    ReviewDTO getReviewById(Long id);

    Long submitReview(Review review);

    DetailedReviewDTO getReviewDetails(Long id);

}
