package com.example.demo.service;

import com.example.demo.controller.dto.Product.SearchProductDto;
import com.example.demo.model.Product;
import com.example.demo.service.argument.CreateProductArgument;
import com.example.demo.service.argument.SearchProductArgument;
import com.example.demo.service.argument.UpdateProductArgument;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> list(SearchProductArgument argument);

    Product create(CreateProductArgument argument);

    Product getExisting(UUID id);

    Product update(UUID id, UpdateProductArgument argument);
}
