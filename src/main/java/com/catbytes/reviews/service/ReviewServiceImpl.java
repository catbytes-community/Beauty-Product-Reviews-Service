package com.catbytes.reviews.service;

import com.catbytes.reviews.dto.*;
import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.entity.Review;
import com.catbytes.reviews.entity.User;
import com.catbytes.reviews.mapper.DetailedReviewMapper;
import com.catbytes.reviews.mapper.ReviewMapper;
import com.catbytes.reviews.repository.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Value("${rate.min}")
    private int MIN;

    @Value("${rate.max}")
    private int MAX;

    private static final Logger LOG = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private final ReviewRepository reviewRepository;
    private final ProductService productService;
    private final ReviewMapper reviewMapper;
    private final DetailedReviewMapper detailedReviewMapper;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductService productService,
                             ReviewMapper reviewMapper, DetailedReviewMapper detailedReviewMapper) {

        this.reviewRepository = reviewRepository;
        this.productService = productService;
        this.reviewMapper = reviewMapper;
        this.detailedReviewMapper = detailedReviewMapper;
    }

    @Override
    public ReviewDTO getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
        return reviewMapper.toDTO(review);
    }

    @Override
    public Long submitReview(Review review) {
        LOG.debug("Submitting review in service");

        if (review.getRate() < MIN || review.getRate() > MAX) {
            throw new IllegalArgumentException("Rate must be between " + MIN + " and " + MAX);
        }

        review.setProduct(productService.findOrCreateProduct(review.getProduct()));
        reviewRepository.save(review);
        return review.getId();
    }

    @Override
    public DetailedReviewDTO getReviewDetails(Long id) {
        // Getting the Review entity
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review with id " + id + " not found"));

        // Get the User entity
        User user = review.getUser();
        if (user == null) {
            throw new IllegalArgumentException("User for review with id " + id + " not found");
        }

        // Get the Product entity
        Product product = review.getProduct();
        if (product == null) {
            throw new IllegalArgumentException("Product for review with id " + id + " not found");
        }

        // Using DetailedReviewMapper to create DTO
        return detailedReviewMapper.toDTO(review, user, product);
    }
}


