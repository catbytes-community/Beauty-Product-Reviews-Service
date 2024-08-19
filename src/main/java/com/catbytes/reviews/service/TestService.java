package com.catbytes.reviews.service;

import com.catbytes.reviews.entity.TestEntity;


public interface TestService {

    Long createEntity(String name);
    TestEntity getEntity(Long id);

}
