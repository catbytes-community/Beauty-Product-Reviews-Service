package com.catbytes.reviews.mapper;

import com.catbytes.reviews.dto.ReviewDTO;
import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.entity.Review;
import com.catbytes.reviews.entity.User;
import com.catbytes.reviews.repository.ProductRepository;
import com.catbytes.reviews.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReviewMapper {

    private static final Logger LOG = LoggerFactory.getLogger(ReviewMapper.class);

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ReviewMapper(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public ReviewDTO convertToDTO(Review review) {
        LOG.info("Starting to convertToDTO");
        if (review == null) {
            LOG.warn("review is null");
            return null;
        }
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setUserId(review.getUser().getId());
        reviewDTO.setProductId(review.getProduct().getId());
        reviewDTO.setHeadline(review.getHeadline());
        reviewDTO.setDescription(review.getDescription());
        reviewDTO.setRate(review.getRate());
        reviewDTO.setCreatedAt(review.getCreatedAt());

        LOG.debug("convert to DTO successful");
        return reviewDTO;
    }

    public Review convertToEntity(ReviewDTO reviewDTO) {
        LOG.info("Starting to convertToEntity");
        if (reviewDTO == null) {
            throw new IllegalArgumentException("reviewDTO is null");
        }

        User user = userRepository.findUserById(reviewDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        //TODO:заглушка
        Product product = productRepository.findProductById(reviewDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setHeadline(reviewDTO.getHeadline());
        review.setDescription(reviewDTO.getDescription());
        review.setRate(reviewDTO.getRate());
        review.setCreatedAt(LocalDateTime.now());
        return review;
    }

}
