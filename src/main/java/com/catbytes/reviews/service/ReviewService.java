package com.catbytes.reviews.service;

import com.catbytes.reviews.dto.ReviewDTO;

public interface ReviewService {

    // Method for getting feedback by id
    ReviewDTO getReviewById(Long id);

}
