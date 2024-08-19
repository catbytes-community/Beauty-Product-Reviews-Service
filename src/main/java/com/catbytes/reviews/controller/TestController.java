package com.catbytes.reviews.controller;

import com.catbytes.reviews.dto.TestDTO;
import com.catbytes.reviews.entity.TestEntity;
import com.catbytes.reviews.mapper.TestMapper;
import com.catbytes.reviews.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    private TestService testService;
    private TestMapper testMapper;

    @Autowired
    public TestController(TestService testService, TestMapper testMapper) {
        this.testService = testService;
        this.testMapper = testMapper;
    }

    @PostMapping
    public long createEntity(@RequestBody String name){
        return testService.createEntity(name);
    }

    @GetMapping("/{id}")
    public TestDTO getEntity(@PathVariable Long id){
        TestEntity entity = testService.getEntity(id);
        return testMapper.convertToDTO(entity);
    }
}
