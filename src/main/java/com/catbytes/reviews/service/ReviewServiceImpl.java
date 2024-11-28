package com.catbytes.reviews.service;

import com.catbytes.reviews.dto.ReviewDTO;
import com.catbytes.reviews.entity.Review;
import com.catbytes.reviews.mapper.ReviewMapper;
import com.catbytes.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ReviewDTO getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
        return ReviewMapper.toDto(review);
    }
    
}
