package com.catbytes.reviews.repository;

import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByNameAndBrand(String name, Brand brand);

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    //TODO: implement retrieving rates from assigned Reviews after [#11] - Review Entity and Review Posting API

    Optional<Product> findProductById(Long id);

}