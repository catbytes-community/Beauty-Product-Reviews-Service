package com.catbytes.reviews.controller.rest;

import com.catbytes.reviews.dto.ReviewDTO;
import com.catbytes.reviews.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
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

}
