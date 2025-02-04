package com.catbytes.reviews.mapper;

import com.catbytes.reviews.dto.DetailedReviewDTO;
import com.catbytes.reviews.dto.ProductDTO;
import com.catbytes.reviews.dto.ReviewDTO;
import com.catbytes.reviews.dto.UserDTO;
import com.catbytes.reviews.entity.Review;
import com.catbytes.reviews.entity.User;
import com.catbytes.reviews.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DetailedReviewMapper {

    private static final Logger LOG = LoggerFactory.getLogger(DetailedReviewMapper.class);

    private final ReviewMapper reviewMapper;
    private final ProductMapper productMapper;
    private final UserMapper userMapper;

    public DetailedReviewMapper(ReviewMapper reviewMapper, ProductMapper productMapper, UserMapper userMapper) {
        this.reviewMapper = reviewMapper;
        this.productMapper = productMapper;
        this.userMapper = userMapper;
    }

    public DetailedReviewDTO toDTO(Review review, User user, Product product) {
        LOG.info("Mapping Review, User, and Product to DetailedReviewDTO");

        if (review == null || user == null || product == null) {
            LOG.warn("One of the required entities is null");
            return null;
        }

        // Use mappers to transform entities into DTOs
        ReviewDTO reviewDTO = reviewMapper.toDTO(review);
        UserDTO userDTO = userMapper.toDTO(user);
        ProductDTO productDTO = productMapper.toDTO(product);

        // Create DetailedReviewDTO and fill it with data
        DetailedReviewDTO detailedReviewDTO = new DetailedReviewDTO();
        detailedReviewDTO.setReview(reviewDTO);
        detailedReviewDTO.setUser(userDTO);
        detailedReviewDTO.setProduct(productDTO);

        LOG.debug("Mapped to DetailedReviewDTO successfully");
        return detailedReviewDTO;
    }
}
