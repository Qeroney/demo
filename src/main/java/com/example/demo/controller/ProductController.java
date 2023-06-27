package com.example.demo.controller;

import com.example.demo.action.CreateProductAction;
import com.example.demo.action.CreateProductActionArgument;
import com.example.demo.controller.dto.CreateProductDto;
import com.example.demo.controller.dto.ProductDto;
import com.example.demo.controller.dto.UpdateProductDto;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.argument.CreateProductArgument;
import com.example.demo.service.argument.UpdateProductArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final CreateProductAction createProductAction;

    @GetMapping("list")
    public List<ProductDto> list() {
        return productService.list().stream()
                             .map(product -> ProductDto.builder()
                                                       .id(product.getId())
                                                       .title(product.getTitle())
                                                       .price(product.getPrice())
                                                       .build())
                             .collect(Collectors.toList());
    }

    @PostMapping("create")
    public ProductDto create(@RequestBody CreateProductDto dto) {
        Product product = productService.create(CreateProductArgument.builder()
                                                                     .price(dto.getPrice())
                                                                     .title(dto.getTitle())
                                                                     .build());
        return ProductDto.builder()
                         .id(product.getId())
                         .title(product.getTitle())
                         .price(product.getPrice())
                         .build();
    }

    @PutMapping("update/{id}")
    public ProductDto update(@PathVariable UUID id, @RequestBody UpdateProductDto dto) {
        UpdateProductArgument argument = UpdateProductArgument.builder()
                .title(dto.getTitle())
                .price(dto.getPrice())
                .build();
        Product product = productService.update(id, argument);
        return ProductDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .build();
    }
}
