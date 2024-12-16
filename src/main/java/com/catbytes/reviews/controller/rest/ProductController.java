package com.catbytes.reviews.controller.rest;

import com.catbytes.reviews.dto.ProductDTO;
import com.catbytes.reviews.entity.Brand;
import com.catbytes.reviews.entity.Category;
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
            products = productService.getAllProducts(limit, sortBy, direction);
        } else {
            products = productService.findByNameContainingIgnoreCase(name, limit, sortBy, direction);
        }

        return products.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/brand")
    @Operation(summary = "Get available brands",
            description = "Find brands by containing name value with ignoring case. If no name is provided, returns all brands.")
    public List<String> getAllBrands(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction) {

        List<Brand> brands;
        if (name == null || name.isEmpty()) {
            brands = productService.getAllBrands(sortBy, direction, limit);
        } else {
            brands = productService.findBrandsByNameContainingIgnoreCase(name, limit, sortBy, direction);
        }

        return brands.stream()
                .map(Brand::getName)
                .collect(Collectors.toList());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Add a new product",
            description = "Creates a new product. If the brand does not exist, it will be created.")
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        // Find the category
        Category category = productService.findCategoryById(productDTO.getCategoryId());
        // Convert the DTO to an entity, passing in the category and brand
        Product product = productMapper.toEntity(productDTO, category);

        Product savedProduct = productService.addProduct(product);
        return productMapper.toDTO(savedProduct);
    }

}
