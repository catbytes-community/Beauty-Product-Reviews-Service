package com.catbytes.reviews.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public class ReviewDTO {


    @Schema(description = "The unique identifier of the review", example = "1")
    private Long id;

    @Schema(description = "ID of the user who submitted the review", example = "123")
    @NotNull
    private Long userId;

    @Schema(description = "Product being reviewed")
    @Valid
    @NotNull
    private ProductDTO productDTO;

    @Schema(description = "Headline or title of the review", example = "Great product!")
    @NotEmpty(message = "headline should not be empty")
    @Size(min = 2, max = 200)
    private String headline;

    @Schema(description = "Detailed description of the review", example = "This product exceeded my expectations.")
    @NotEmpty(message = "description should not be empty")
    private String description;

    @Schema(description = "Rating given to the product", example = "4")
    @NotNull
    @Min(1)
    @Max(5)
    private Integer rate;

    @Schema(description = "Timestamp when the review was created")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp when the review was last updated")
    private LocalDateTime updatedAt;

    public ReviewDTO(Long id, Long userId, ProductDTO productDTO, String headline, String description, Integer rate, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.productDTO = productDTO;
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

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
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
                ", productDTO=" + productDTO +
                ", headline='" + headline + '\'' +
                ", description='" + description + '\'' +
                ", rate=" + rate +
                ", createdAt=" + createdAt +
                '}';
    }
}
