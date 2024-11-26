package com.catbytes.reviews.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class BrandDTO {

    @Schema(description = "The unique identifier of the brand", example = "1")
    private Long id;

    @Schema(description = "Brand name", example = "Clinique")
    private String name;

    public BrandDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public BrandDTO() {
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
}