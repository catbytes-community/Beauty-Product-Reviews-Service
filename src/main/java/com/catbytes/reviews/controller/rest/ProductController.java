package com.catbytes.reviews.controller.rest;

import com.catbytes.reviews.dto.ProductDTO;
import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.mapper.ProductMapper; // Добавляем импорт для ProductMapper
import com.catbytes.reviews.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper; // Добавляем поле для ProductMapper

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper; // Инжектируем ProductMapper
    }

    @PostMapping
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        Product product = productService.addProduct(productDTO);
        return productMapper.toDTO(product);
    }

    @GetMapping("/findByName")
    public ProductDTO findByName(@RequestParam("name") String name) {
        Optional<Product> product = productService.findByName(name);
        return product.map(productMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    @DeleteMapping("/{id}")
    public ProductDTO deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ProductDTO(id, "Product with ID " + id + " was successfully deleted.");
    }


    @GetMapping
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return products.stream().map(productMapper::toDTO).collect(Collectors.toList());
    }
}
