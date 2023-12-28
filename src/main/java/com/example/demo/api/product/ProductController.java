package com.example.demo.api.product;

import com.example.demo.action.create.CreateProductAction;
import com.example.demo.action.update.UpdateProductAction;
import com.example.demo.action.update.UpdateProductActionArgument;
import com.example.demo.aspect.annotation.LogProductCreation;
import com.example.demo.api.product.dto.CreateProductDto;
import com.example.demo.api.product.dto.ProductDto;
import com.example.demo.api.product.dto.SearchProductDto;
import com.example.demo.api.product.dto.UpdateProductDto;
import com.example.demo.model.Product;
import com.example.demo.service.product.ProductService;
import com.example.demo.service.product.argument.SearchProductArgument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.demo.api.product.mapper.ProductMapper.PRODUCT_MAPPER;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    public ProductController(ProductService productService, CreateProductAction createProductAction, UpdateProductAction updateProductAction) {
        this.productService = productService;
        this.createProductAction = createProductAction;
        this.updateProductAction = updateProductAction;
    }

    private final ProductService productService;

    private final CreateProductAction createProductAction;

    private final UpdateProductAction updateProductAction;

    @GetMapping("list")
    public List<ProductDto> list(SearchProductDto dto) {
        SearchProductArgument argument = PRODUCT_MAPPER.toSearchArgument(dto);

        return productService.list(argument)
                             .stream()
                             .map(PRODUCT_MAPPER::toDto)
                             .collect(Collectors.toList());

    }

    @PostMapping("create")
    @LogProductCreation
    public ProductDto create(@RequestBody CreateProductDto dto) {
        Product product = createProductAction.execute(PRODUCT_MAPPER.toCreateActionArgument(dto));

        return PRODUCT_MAPPER.toDto(product);
    }

    @PutMapping("update/{id}")
    public ProductDto update(@PathVariable UUID id, @RequestBody UpdateProductDto dto) {
        UpdateProductActionArgument argument = PRODUCT_MAPPER.toUpdateActionArgument(dto);
        Product product = updateProductAction.execute(id, argument);
        return PRODUCT_MAPPER.toDto(product);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable UUID id) {
        productService.delete(id);
    }
}
