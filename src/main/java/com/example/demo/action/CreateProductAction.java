package com.example.demo.action;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.argument.CreateProductArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateProductAction {

    private final ProductService productService;


    public Product execute(CreateProductActionArgument argument){

        //todo Implement CategoryService. Use categoryService.getExisting(argument.getCategoryId());

        Product product = productService.create(CreateProductArgument.builder()
                .price(argument.getPrice())
                .title(argument.getTitle())
//                                                        .category() //todo insert category
                .build());
        return null;//todo do create
    }
}
