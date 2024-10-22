package com.catbytes.reviews.controller.rest;

import com.catbytes.reviews.dto.ProductDTO;
import com.catbytes.reviews.entity.Brand;
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
    @Operation(summary = "Get all available brands",
            description = "Returns a list of all available brands.")
    public List<String> getAllBrands() {
        return productService.getAllBrands().stream()
                .map(Brand::getName)
                .collect(Collectors.toList());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Add a new product",
            description = "Creates a new product. If the brand does not exist, it will be created.")
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        // Getting or creating a brand
        Brand brand = productService.addBrand(productDTO.getBrand().getName()); // Get the brand name from the Brand object

        // Convert ProductDTO to Product and set Brand object
        Product product = productMapper.toEntity(productDTO);
        product.setBrand(brand);

        // Save the new product and return the corresponding ProductDTO
        Product savedProduct = productService.addProduct(product);
        return productMapper.toDTO(savedProduct);
    }
}
