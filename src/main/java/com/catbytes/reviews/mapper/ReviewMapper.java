package com.catbytes.reviews.mapper;

import com.catbytes.reviews.dto.ReviewDTO;
import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.entity.Review;
import com.catbytes.reviews.entity.User;
import com.catbytes.reviews.repository.ProductRepository;
import com.catbytes.reviews.repository.UserRepository;
import com.catbytes.reviews.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReviewMapper {

    private static final Logger LOG = LoggerFactory.getLogger(ReviewMapper.class);

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ReviewMapper(UserRepository userRepository, ProductRepository productRepository, ProductService productService, ProductMapper productMapper) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productService = productService;
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
        reviewDTO.setUserId(review.getUser().getId());
        reviewDTO.setProductDTO(productMapper.toDTO(review.getProduct()));
        reviewDTO.setHeadline(review.getHeadline());
        reviewDTO.setDescription(review.getDescription());
        reviewDTO.setRate(review.getRate());
        reviewDTO.setCreatedAt(review.getCreatedAt());

        LOG.debug("convert to DTO successful");
        return reviewDTO;
    }

    public Review toEntity(ReviewDTO reviewDTO) {
        LOG.info("Starting to toEntity");
        if (reviewDTO == null) {
            throw new IllegalArgumentException("reviewDTO is null");
        }

        User user = userRepository.findUserById(reviewDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Product product = productMapper.toEntity(reviewDTO.getProductDTO());

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
