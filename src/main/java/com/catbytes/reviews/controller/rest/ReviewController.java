package com.catbytes.reviews.controller.rest;

import com.catbytes.reviews.dto.ProductDTO;
import com.catbytes.reviews.dto.ReviewDTO;
import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.mapper.ProductMapper;
import com.catbytes.reviews.mapper.ReviewMapper;
import com.catbytes.reviews.service.ProductService;
import com.catbytes.reviews.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Reviews", description = "API for managing product reviews")
public class ReviewController {

    private static final Logger LOG = LoggerFactory.getLogger(ReviewController.class);

    private final ReviewService reviewService;
    private  final ProductService productService;
    private final ProductMapper productMapper;

    @Autowired
    public ReviewController(ReviewService reviewService, ProductService productService, ProductMapper productMapper) {
        this.reviewService = reviewService;
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping("/reviews")
    @Operation(summary = "Submit a product review",
            description = "Submits a review for a specified product by a user.")
    void submitReview(@RequestBody @Valid ReviewDTO reviewDTO) {
        LOG.info("Attempting to submit a review for product by userId: {}", reviewDTO.getUserId());
        reviewService.submitReview(reviewDTO);
        LOG.info("Review by userId: {} submitted successfully", reviewDTO.getUserId());
    }
}