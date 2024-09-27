package com.catbytes.reviews.service;

import com.catbytes.reviews.entity.TestEntity;
import com.catbytes.reviews.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleTestService implements TestService {

    private final TestRepository testRepository;

    @Autowired
    public SimpleTestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public Long createEntity(String name){
        TestEntity testEntity = new TestEntity();
        testEntity.setName(name);
        testRepository.save(testEntity);
        return testEntity.getId();
    }

    @Override
    public TestEntity getEntity(Long id){
        return testRepository.getReferenceById(id);
    }
}
