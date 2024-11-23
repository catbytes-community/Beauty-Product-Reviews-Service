package com.catbytes.reviews.repository;

import com.catbytes.reviews.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT t FROM Category t WHERE t.parent.id = :parent_id")
    List<Category> findByParentId(@Param("parent_id") Long id);

    @Query("SELECT t FROM Category t WHERE t.parent.id IS NULL")
    List<Category> findRootCategories();

    boolean existsBy();
}
