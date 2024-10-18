package com.catbytes.reviews.controller.rest;

import com.catbytes.reviews.dto.ProductDTO;
import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.mapper.ProductMapper;
import com.catbytes.reviews.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
    public List<ProductDTO> findByNameContainingIgnoreCase(@RequestParam("name") String name) {
        List<Product> products = productService.findByNameContainingIgnoreCase(name);

        if (products.isEmpty()) {
            throw new IllegalArgumentException("Product not found");
        }

        // Convert the list of products to a list of DTOs
        return products.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }


    @GetMapping
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return products.stream().map(productMapper::toDTO).collect(Collectors.toList());
    }
}
