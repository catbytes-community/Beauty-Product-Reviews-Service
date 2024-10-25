package com.catbytes.reviews.dto;

import java.time.LocalDateTime;

public class ReviewDTO {

    private Long id;

    private Long userId;

    private Long productId;

    private String headline;

    private String description;

    private Integer rate;

    private LocalDateTime createdAt;


    public ReviewDTO(Long id, Long userId, Long productId, String headline, String description, Integer rate, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.headline = headline;
        this.description = description;
        this.rate = rate;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", headline='" + headline + '\'' +
                ", description='" + description + '\'' +
                ", rate=" + rate +
                ", createdAt=" + createdAt +
                '}';
    }
}