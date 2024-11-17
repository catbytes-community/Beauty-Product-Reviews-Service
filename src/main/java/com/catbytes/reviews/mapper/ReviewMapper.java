package com.catbytes.reviews.mapper;

import com.catbytes.reviews.dto.ReviewDTO;
import com.catbytes.reviews.entity.Review;
import com.catbytes.reviews.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReviewMapper {

    private static final Logger LOG = LoggerFactory.getLogger(ReviewMapper.class);

    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    public ReviewMapper(UserRepository userRepository, ProductMapper productMapper) {
        this.userRepository = userRepository;
        this.productMapper = productMapper;
    }

    public ReviewDTO toDTO(Review review) {
        LOG.info("Starting to convertToDTO");
        if (review == null) {
            LOG.warn("review is null");
            return null;
        }
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setHeadline(review.getHeadline());
        reviewDTO.setDescription(review.getDescription());
        reviewDTO.setRate(review.getRate());
        reviewDTO.setCreatedAt(review.getCreatedAt());

        LOG.debug("convert to DTO successful");
        return reviewDTO;
    }

    public Review toEntity(ReviewDTO reviewDTO) {
        LOG.info("Starting to convert ReviewDTO to Review entity");
        if (reviewDTO == null) {
            throw new IllegalArgumentException("reviewDTO is null");
        }

        Review review = new Review();
        review.setHeadline(reviewDTO.getHeadline());
        review.setDescription(reviewDTO.getDescription());
        review.setRate(reviewDTO.getRate());
        review.setCreatedAt(LocalDateTime.now());
        LOG.debug("Converted ReviewDTO to Review entity successfully");
        return review;
    }

}
