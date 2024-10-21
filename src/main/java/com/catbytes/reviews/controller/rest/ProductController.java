package com.catbytes.reviews.controller.rest;

import com.catbytes.reviews.dto.ProductDTO;
import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.mapper.ProductMapper;
import com.catbytes.reviews.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@Tag(name = "Product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Get products list",
            description = "Find products by containing name value with ignoring case. If no name is provided, returns all products.")
    public List<ProductDTO> getProducts(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction) {

        List<Product> products;
        if (name == null || name.isEmpty()) {
            // If no name is provided, fetch all products
            products = productService.getAllProducts(limit, sortBy, direction);
        } else {
            // If a name is provided, search by name (ignoring case)
            products = productService.findByNameContainingIgnoreCase(name, limit, sortBy, direction);
        }

        // Convert entities to DTO
        return products.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }


}
