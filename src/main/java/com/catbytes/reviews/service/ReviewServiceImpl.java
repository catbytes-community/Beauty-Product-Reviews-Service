package com.catbytes.reviews.service;

import com.catbytes.reviews.dto.ReviewDTO;
import com.catbytes.reviews.mapper.ProductMapper;
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
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper, ProductService productService, ProductMapper productMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.productService = productService;
        this.productMapper = productMapper;
    }

    public void submitReview(ReviewDTO reviewDTO) {
        LOG.debug("Submitting review in service");
        if (reviewDTO.getProductDTO() == null || reviewDTO.getProductDTO().getId() == null) {
            LOG.info("Product not found in the database. Adding new product.");
            productService.addProduct(productMapper.toEntity(reviewDTO.getProductDTO()));
        }
        reviewRepository.save(reviewMapper.toEntity(reviewDTO));
    }

}
