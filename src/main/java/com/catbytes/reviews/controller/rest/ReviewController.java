package com.catbytes.reviews.controller.rest;

import com.catbytes.reviews.dto.ReviewDTO;
import com.catbytes.reviews.repository.CategoryRepository;
import com.catbytes.reviews.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ReviewController(ReviewService reviewService, CategoryRepository categoryRepository) {
        this.reviewService = reviewService;
        this.categoryRepository = categoryRepository;
    }

    @Operation(summary = "Retrieve detailed information about a specific review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved review details"),
            @ApiResponse(responseCode = "404", description = "Review not found")
    })

    @GetMapping("/{id}")
    public ReviewDTO getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    @Operation(summary = "Get available filter values for reviews")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filter values successfully retrieved")
    })

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

        // List of ratings
        List<Integer> ratings = IntStream.rangeClosed(1, 5).boxed().collect(Collectors.toList());

        // Final response structure
        Map<String, Object> response = new HashMap<>();
        response.put("categories", categories);
        response.put("ratings", ratings);

        return response;
    }
}
