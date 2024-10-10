package com.catbytes.reviews.repository;

import com.catbytes.reviews.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByNameAndBrand(String name, String brand);

    Optional<Product> findByName(String name);

    //TODO: implement retrieving rates from assigned Reviews after [#11] - Review Entity and Review Posting API

}