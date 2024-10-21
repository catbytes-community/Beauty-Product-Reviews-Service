package com.catbytes.reviews.controller.rest;

import com.catbytes.reviews.dto.ProductDTO;
import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.mapper.ProductMapper;
import com.catbytes.reviews.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping("/findByName")
    public List<ProductDTO> findByNameContainingIgnoreCase(
            @RequestParam("name") String name,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction) {

        // We receive products taking into account the limit and sorting
        List<Product> products = productService.findByNameContainingIgnoreCase(name, limit, sortBy, direction);
        if (products.isEmpty()) {
            throw new IllegalArgumentException("Product not found");
        }

        // Convert entities to DTO
        return products.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<ProductDTO> getAllProducts(
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction) {

        // We receive products taking into account the limit and sorting
        List<Product> products = productService.getAllProducts(limit, sortBy, direction);

        // Convert entities to DTO
        return products.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }


}
