package com.catbytes.reviews.controller.rest;

import com.catbytes.reviews.dto.DetailedReviewDTO;
import com.catbytes.reviews.dto.SubmitReviewDTO;
import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.entity.Review;
import com.catbytes.reviews.entity.User;
import com.catbytes.reviews.facade.ReviewFacade;
import com.catbytes.reviews.mapper.ProductMapper;
import com.catbytes.reviews.mapper.ReviewMapper;
import com.catbytes.reviews.repository.UserRepository;
import com.catbytes.reviews.repository.CategoryRepository;
import com.catbytes.reviews.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Reviews", description = "API for managing product reviews")
@RequestMapping("/review")
public class ReviewController {

    private static final Logger LOG = LoggerFactory.getLogger(ReviewController.class);

    private final ReviewService reviewService;
    private final ReviewFacade reviewFacade;
    private final CategoryRepository categoryRepository;
    private final ReviewMapper reviewMapper;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    @Value("${rate.min}")
    private int rateMin;

    @Value("${rate.max}")
    private int rateMax;

    @Autowired
    public ReviewController(ReviewService reviewService, ReviewFacade reviewFacade,
                            CategoryRepository categoryRepository, ReviewMapper reviewMapper,
                            UserRepository userRepository, ProductMapper productMapper) {
        this.reviewService = reviewService;
        this.reviewFacade = reviewFacade;
        this.reviewMapper = reviewMapper;
        this.userRepository = userRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
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

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve detailed information about a specific review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved review details"),
            @ApiResponse(responseCode = "404", description = "Review not found")
    })
    public DetailedReviewDTO getReviewDetails(@PathVariable Long id) {
        LOG.info("Retrieving details for review with id: {}", id);
        return reviewFacade.getReviewDetails(id); // Используем фасад вместо сервиса
    }

    @GetMapping("/filter")
    public Map<String, Object> getFilterValues() {
        // List of categories
        List<Map<String, Object>> categories = categoryRepository.findAll().stream()
                .map(category -> {
                    Map<String, Object> categoryMap = new HashMap<>();
                    categoryMap.put("id", category.getId());
                    categoryMap.put("name", category.getName());
                    return categoryMap;
                })
                .collect(Collectors.toList());

        // List of ratings from min to max
        List<Integer> ratings = IntStream.rangeClosed(rateMin, rateMax).boxed().collect(Collectors.toList());

        // Final response structure
        Map<String, Object> response = new HashMap<>();
        response.put("categories", categories);
        response.put("ratings", ratings);

        return response;
    }
}

