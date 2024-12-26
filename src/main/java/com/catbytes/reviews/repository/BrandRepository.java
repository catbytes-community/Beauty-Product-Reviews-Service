package com.catbytes.reviews.repository;

import com.catbytes.reviews.entity.Brand;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findByNameIgnoreCase(String name);

    List<Brand> findByNameContainingIgnoreCase(String name, Pageable pageable);


}
