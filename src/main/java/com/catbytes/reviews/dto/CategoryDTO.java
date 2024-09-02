package com.catbytes.reviews.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDTO {
    private int id;
    private String name;
    private Integer parentId;
    private List<CategoryDTO> subCategories;

    public CategoryDTO() {}
    public CategoryDTO(long id, String name, long parentId, List<CategoryDTO> subCategories) {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
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
