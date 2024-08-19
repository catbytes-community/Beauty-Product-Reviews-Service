package com.catbytes.reviews.mapper;

import com.catbytes.reviews.dto.TestDTO;
import com.catbytes.reviews.entity.TestEntity;
import org.springframework.stereotype.Component;

@Component
public class TestMapper {

    public TestDTO convertToDTO(TestEntity testEntity) {
        TestDTO testDTO = new TestDTO();
        testDTO.setId(testEntity.getId());
        testDTO.setName(testEntity.getName());
        return testDTO;
    }

}
