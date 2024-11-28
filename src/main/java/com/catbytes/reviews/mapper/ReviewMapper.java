package com.catbytes.reviews.mapper;

import com.catbytes.reviews.dto.ReviewDTO;
import com.catbytes.reviews.entity.Review;

public class ReviewMapper {

    public static ReviewDTO toDto(Review review) {
        return new ReviewDTO(
                review.getId(),
                review.getUser().getId(),
                review.getProduct().getId(),
                review.getHeadline(),
                review.getDescription(),
                review.getRate(),
                review.getCreatedAt()
        );
    }
}
