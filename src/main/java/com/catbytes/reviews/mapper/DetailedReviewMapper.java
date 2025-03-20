package com.catbytes.reviews.mapper;

import com.catbytes.reviews.dto.*;
import com.catbytes.reviews.entity.Review;
import com.catbytes.reviews.entity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DetailedReviewMapper {

    private static final Logger LOG = LoggerFactory.getLogger(DetailedReviewMapper.class);

    private final ReviewMapper reviewMapper;
    private final ProductMapper productMapper;
    private final UserMapper userMapper;
    private final CategoryMapper categoryMapper;

    public DetailedReviewMapper(ReviewMapper reviewMapper, ProductMapper productMapper, UserMapper userMapper, CategoryMapper categoryMapper) {
        this.reviewMapper = reviewMapper;
        this.productMapper = productMapper;
        this.userMapper = userMapper;
        this.categoryMapper = categoryMapper;
    }

    public DetailedReviewDTO toDTO(Review review) {
        LOG.info("Mapping Review, User, Product, and Category to DetailedReviewDTO");

        if (review == null) {
            throw new IllegalArgumentException("Review cannot be null");
        }
        if (review.getUser() == null) {
            throw new IllegalArgumentException("User for review with id " + review.getId() + " not found");
        }
        if (review.getProduct() == null) {
            throw new IllegalArgumentException("Product for review with id " + review.getId() + " not found");
        }
        Category category = review.getProduct().getCategory();
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }

        // Use mappers to transform entities into DTOs
        ReviewDTO reviewDTO = reviewMapper.toDTO(review);
        UserDTO userDTO = userMapper.toDTO(review.getUser());
        ProductDTO productDTO = productMapper.toDTO(review.getProduct());
        CategoryDTO categoryDTO = categoryMapper.convertToDTO(category);

        // Create DetailedReviewDTO and fill it with data
        DetailedReviewDTO detailedReviewDTO = new DetailedReviewDTO();
        detailedReviewDTO.setReview(reviewDTO);
        detailedReviewDTO.setUser(userDTO);
        detailedReviewDTO.setProduct(productDTO);
        detailedReviewDTO.setCategory(categoryDTO);
        
        LOG.debug("Mapped to DetailedReviewDTO successfully");
        return detailedReviewDTO;
    }
}
