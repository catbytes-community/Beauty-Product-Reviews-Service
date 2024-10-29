package com.catbytes.reviews.service;

import com.catbytes.reviews.dto.ReviewDTO;
import com.catbytes.reviews.mapper.ReviewMapper;
import com.catbytes.reviews.repository.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private static final Logger LOG = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;


    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;

    }

    public void submitReview(ReviewDTO reviewDTO) {
        LOG.debug("Submitting review in service");
        reviewRepository.save(reviewMapper.toEntity(reviewDTO));
    }

}
