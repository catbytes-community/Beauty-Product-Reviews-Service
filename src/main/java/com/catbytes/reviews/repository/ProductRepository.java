package com.catbytes.reviews.repository;

import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

     //TODO: implement retrieving rates from assigned Reviews after [#11] - Review Entity and Review Posting API

}
