package com.catbytes.reviews.dto;

import com.catbytes.reviews.entity.Brand;
import io.swagger.v3.oas.annotations.media.Schema;

public class ProductDTO {

    @Schema(description = "The unique identifier of product", example = "1")
    private Long id;
    @Schema(description = "Product name", example = "Moisture Surge Gel")
    private String name;
    @Schema(description = "Product brand", example = "Clinique")
    private Brand brand;
    @Schema(description = "Category identifier", example = "3")
    private Long categoryId;
    @Schema(description = "Product average rating", example = "4.5")
    private Double averageRating;

    public ProductDTO(Long id, String name, Brand brand, Long categoryId, Double averageRating) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.categoryId = categoryId;
        this.averageRating = averageRating;
    }

    public ProductDTO() {
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
