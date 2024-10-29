package com.catbytes.reviews.controller;

import com.catbytes.reviews.dto.TestDTO;
import com.catbytes.reviews.entity.TestEntity;
import com.catbytes.reviews.mapper.TestMapper;
import com.catbytes.reviews.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@Tag(name = "Test", description = "Test API")
public class TestController {

    private final TestService testService;
    private final TestMapper testMapper;

    @Autowired
    public TestController(TestService testService, TestMapper testMapper) {
        this.testService = testService;
        this.testMapper = testMapper;
    }

    @PostMapping
    @Operation(summary = "Create entity",
            description = "Create test entity and return its id")
    public long createEntity(@RequestBody String name){
        return testService.createEntity(name);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get entity", description = "Return entity by provided id")
    public TestDTO getEntity(@PathVariable Long id){
        TestEntity entity = testService.getEntity(id);
        return testMapper.convertToDTO(entity);
    }
}
