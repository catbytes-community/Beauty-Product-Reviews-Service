package com.catbytes.reviews.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class ReviewDTO {

    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Long productId;

    @NotEmpty(message = "headline should not be empty")
    @Size(min = 2, max = 200)
    private String headline;

    @NotEmpty(message = "description should not be empty")
    private String description;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer rate;

    private LocalDateTime createdAt;

//    TODO:спросить про private LocalDateTime updatedAt;

    public ReviewDTO(Long id, Long userId, Long productId, String headline, String description, Integer rate, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.headline = headline;
        this.description = description;
        this.rate = rate;
        this.createdAt = createdAt;
    }

    public ReviewDTO() {}

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
        return "PeviewDTO{" +
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
