package com.catbytes.reviews.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDTO {

    @Schema(description = "The unique identifier of category", example = "2")
    private Long id;
    @Schema(description = "The name of of category", example = "Moisturizers")
    private String name;
    @Schema(description = "The identifier of parent category", example = "1")
    private Long parentId;
    @Schema(description = "The list of sub-categories", example = "[]")
    private List<CategoryDTO> subCategories;

    public CategoryDTO() {
    }

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<CategoryDTO> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<CategoryDTO> subCategories) {
        this.subCategories = subCategories;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", subCategories=" + subCategories +
                '}';
    }
}
