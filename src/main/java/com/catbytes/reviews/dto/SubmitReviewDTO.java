package com.catbytes.reviews.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class SubmitReviewDTO {

    @Schema(description = "ID of the user who submitted the review", example = "123")
    @NotNull
    private Long userId;

    @Schema(description = "Detailed information about the product being reviewed, including its identifier, name, brand, category, average rating")
    @Valid
    @NotNull
    private ProductDTO productDTO;

    @Schema(description = "Detailed information about the submitted review, including id, headliner, description, rating")
    @Valid
    @NotNull
    private ReviewDTO reviewDTO;

    public SubmitReviewDTO(Long userId, ProductDTO productDTO, ReviewDTO reviewDTO) {
        this.userId = userId;
        this.productDTO = productDTO;
        this.reviewDTO = reviewDTO;
    }

    public SubmitReviewDTO() {
    }

    public @NotNull Long getUserId() {
        return userId;
    }

    public void setUserId(@NotNull Long userId) {
        this.userId = userId;
    }

    public @Valid @NotNull ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(@Valid @NotNull ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public @Valid @NotNull ReviewDTO getReviewDTO() {
        return reviewDTO;
    }

    public void setReviewDTO(@Valid @NotNull ReviewDTO reviewDTO) {
        this.reviewDTO = reviewDTO;
    }
}
