package com.example.demo.action;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.Category.CategoryService;
import com.example.demo.service.Product.ProductService;
import com.example.demo.service.Product.argument.CreateProductArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateProductAction {

     ProductService productService;

     CategoryService categoryService;


    public Product execute(CreateProductActionArgument argument) {
        Category category = categoryService.getExisting(argument.getCategoryId());

        return productService.create(CreateProductArgument.builder()
                .price(argument.getPrice())
                .title(argument.getTitle())
                .category(category)
                .build());
    }
}
