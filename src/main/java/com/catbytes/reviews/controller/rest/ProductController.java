package com.catbytes.reviews.controller.rest;

import com.catbytes.reviews.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //TODO: get all products
}

