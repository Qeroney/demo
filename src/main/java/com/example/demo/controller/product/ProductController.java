package com.example.demo.controller;

import com.example.demo.action.CreateProductAction;
import com.example.demo.controller.Product.UpdateProductDto;
import com.example.demo.controller.Product.CreateProductDto;
import com.example.demo.controller.Product.ProductDto;
import com.example.demo.controller.Product.SearchProductDto;
import com.example.demo.model.Product;
import com.example.demo.service.Product.ProductService;
import com.example.demo.service.argument.Search.SearchProductArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.demo.mapper.ProductMapper.PRODUCT_MAPPER;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final CreateProductAction createProductAction;

    @GetMapping("list")
    public List<ProductDto> list(SearchProductDto dto) {
        SearchProductArgument argument = PRODUCT_MAPPER.toSearchProductArgument(dto);

        return productService.list(argument)
                .stream()
                .map(PRODUCT_MAPPER::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("create")
    public ProductDto create(@RequestBody CreateProductDto dto) {
        Product product = createProductAction.execute(PRODUCT_MAPPER.toCreateActionArgument(dto));

        return PRODUCT_MAPPER.toDto(product);
    }

    @PutMapping("update/{id}")
    public ProductDto update(@PathVariable UUID id, @RequestBody UpdateProductDto dto) {
        Product product = productService.update(id,PRODUCT_MAPPER.toUpdateProductArgument(dto));

        return PRODUCT_MAPPER.toDto(product);
    }
}
