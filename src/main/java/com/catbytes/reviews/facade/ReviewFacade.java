package com.catbytes.reviews.facade;

import com.catbytes.reviews.dto.DetailedReviewDTO;
import com.catbytes.reviews.entity.Category;
import com.catbytes.reviews.entity.Review;
import com.catbytes.reviews.mapper.DetailedReviewMapper;
import com.catbytes.reviews.service.CategoryService;
import com.catbytes.reviews.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewFacade {

    private final ReviewService reviewService;
    private final CategoryService categoryService;
    private final DetailedReviewMapper detailedReviewMapper;

    @Autowired
    public ReviewFacade(ReviewService reviewService, CategoryService categoryService, DetailedReviewMapper detailedReviewMapper) {
        this.reviewService = reviewService;
        this.categoryService = categoryService;
        this.detailedReviewMapper = detailedReviewMapper;
    }

    public DetailedReviewDTO getReviewDetails(Long id) {
        Review review = reviewService.getReviewById(id);
        Category category = categoryService.getCategoryById(review.getProduct().getCategory().getId());
        return detailedReviewMapper.toDTO(review, category);
    }
}
