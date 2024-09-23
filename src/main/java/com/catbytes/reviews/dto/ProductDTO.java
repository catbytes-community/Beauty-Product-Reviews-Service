package com.catbytes.reviews.dto;

public class ProductDTO {
    private Long id;
    private String name;
    private String brand;
    private Long categoryId;
    private String imageUrl;
    private Double averageRating;

    public ProductDTO(Long id, String name, String brand, Long categoryId, String imageUrl, Double averageRating) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
        this.averageRating = averageRating;
    }

    public ProductDTO() {
    }

    // Геттеры и сеттеры
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
