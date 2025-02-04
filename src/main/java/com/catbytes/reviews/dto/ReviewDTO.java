package com.catbytes.reviews.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class ReviewDTO {

    @Schema(description = "The unique identifier of the review", example = "1")
    private Long id;

    @Schema(description = "Headline or title of the review", example = "Great product!")
    @NotEmpty(message = "headline should not be empty")
    @Size(min = 2, max = 200)
    private String headline;

    @Schema(description = "Detailed description of the review", example = "This product exceeded my expectations.")
    @NotEmpty(message = "description should not be empty")
    private String description;

    @Schema(description = "Rating given to the product", example = "4")
    @NotNull
    private Integer rate;

    @Schema(description = "Timestamp when the review was created")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp when the review was last updated")
    private LocalDateTime updatedAt;

    public ReviewDTO(Long id, String headline, String description, Integer rate, LocalDateTime createdAt) {
        this.id = id;
        this.headline = headline;
        this.description = description;
        this.rate = rate;
        this.createdAt = createdAt;
    }

    public ReviewDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                ", headline='" + headline + '\'' +
                ", description='" + description + '\'' +
                ", rate=" + rate +
                ", createdAt=" + createdAt +
                '}';
    }
}
