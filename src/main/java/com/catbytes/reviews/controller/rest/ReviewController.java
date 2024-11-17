package com.catbytes.reviews.controller.rest;

import com.catbytes.reviews.dto.SubmitReviewDTO;
import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.entity.Review;
import com.catbytes.reviews.entity.User;
import com.catbytes.reviews.mapper.ProductMapper;
import com.catbytes.reviews.mapper.ReviewMapper;
import com.catbytes.reviews.repository.UserRepository;
import com.catbytes.reviews.service.ProductService;
import com.catbytes.reviews.service.ReviewService;
import com.catbytes.reviews.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Reviews", description = "API for managing product reviews")
public class ReviewController {

    private static final Logger LOG = LoggerFactory.getLogger(ReviewController.class);

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ReviewController(ReviewService reviewService, ReviewMapper reviewMapper, UserRepository userRepository, ProductMapper productMapper) {
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
        this.userRepository = userRepository;
        this.productMapper = productMapper;
    }

    @PostMapping("/reviews")
    @Operation(summary = "Submit a product review",
            description = "Submits a review for a specified product by a user.")
    public Long submitReview(@RequestBody @Valid SubmitReviewDTO submitReviewDTO) {
        LOG.info("Attempting to submit a review for product by userId: {}", submitReviewDTO.getUserId());

        User user = userRepository.findUserById(submitReviewDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Product product = productMapper.toEntity(submitReviewDTO.getProductDTO());
        Review review = reviewMapper.toEntity(submitReviewDTO.getReviewDTO());
        review.setUser(user);
        review.setProduct(product);

        return reviewService.submitReview(review);
    }
}