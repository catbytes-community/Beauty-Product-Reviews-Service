package com.catbytes.reviews.service;

import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) {
        //TODO: add validation: pare brand-name has to be unique
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    //TODO: add find by Name

    //TODO: remove product

    @Override
    public Double calculateAverageRating(Long productId) {
        //TODO: implement after [#11] - Review Entity and Review Posting API
        return null;
    }

}
