package com.example.demo.service;

import com.example.demo.controller.dto.Product.SearchProductDto;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.argument.CreateProductArgument;
import com.example.demo.service.argument.SearchProductArgument;
import com.example.demo.service.argument.UpdateProductArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public List<Product> list(SearchProductArgument argument) {
        return repository.findAll();
    }

    @Override
    public Product create(CreateProductArgument argument) {
        return repository.save(Product.builder()
                                      .title(argument.getTitle())
                                      .price(argument.getPrice())
                                      .build());
    }

    @Override
    public Product getExisting(UUID id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Product update(UUID id, UpdateProductArgument argument) {
        Product product = getExisting(id);

        product.setTitle(argument.getTitle());
        product.setPrice(argument.getPrice());

        return repository.save(product);
    }
}
