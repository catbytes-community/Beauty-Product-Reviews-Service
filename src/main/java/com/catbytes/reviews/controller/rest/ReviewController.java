package com.catbytes.reviews.controller.rest;

import com.catbytes.reviews.dto.ReviewDTO;
import com.catbytes.reviews.mapper.ReviewMapper;
import com.catbytes.reviews.service.ReviewService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    private static final Logger LOG = LoggerFactory.getLogger(RestController.class);

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/reviews")
    void submitReview(@RequestBody @Valid ReviewDTO reviewDTO) {
        LOG.info("Attempting to submit a review for productId: {} by userId: {}", reviewDTO.getProductId(), reviewDTO.getUserId());
        reviewService.submitReview(reviewDTO);
        LOG.info("Review for productId: {} by userId: {} submitted successfully", reviewDTO.getProductId(), reviewDTO.getUserId());
    }
}
