package com.catbytes.reviews.service;

import com.catbytes.reviews.dto.ReviewDTO;
import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.entity.Review;
import com.catbytes.reviews.entity.User;
import com.catbytes.reviews.repository.CategoryRepository;
import com.catbytes.reviews.repository.ReviewRepository;
import com.catbytes.reviews.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewServiceImpl implements ReviewService {

    private static final Logger LOG = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public void submitReview(ReviewDTO reviewDTO) {

    User user = userRepository.findUserById(reviewDTO.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

    //TODO:
    Product product = productRepository.findProductById(reviewDTO.getProductId())
            .orElseThrow(() -> new IllegalArgumentException("Product not found"));

    Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setHeadline(reviewDTO.getHeadline());
        review.setDescription(reviewDTO.getDescription());
        review.setRate(reviewDTO.getRate());
        review.setCreatedAt(LocalDateTime.now());

        reviewRepository.save(review);
}

}
