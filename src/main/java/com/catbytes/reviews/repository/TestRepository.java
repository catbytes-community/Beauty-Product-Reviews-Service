package com.catbytes.reviews.repository;

import com.catbytes.reviews.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Long> {

    @Query("SELECT t FROM TestEntity t WHERE t.name = :name")
    List<TestEntity> findByName(@Param("name") String name);

}
