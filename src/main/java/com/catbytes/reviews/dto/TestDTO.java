package com.catbytes.reviews.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class TestDTO {

    @Schema(description = "The unique identifier of the entity", example = "1")
    private Long id;
    @Schema(description = "The name of the entity", example = "TestEntity")
    private String name;

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
