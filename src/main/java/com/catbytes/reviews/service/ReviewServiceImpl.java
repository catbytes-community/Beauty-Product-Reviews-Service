package com.catbytes.reviews.service;

import com.catbytes.reviews.dto.ReviewDTO;
import com.catbytes.reviews.entity.Review;
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

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductService productService, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.productService = productService;
        this.reviewMapper = reviewMapper;
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

}
