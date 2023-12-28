package com.example.demo.api.product.mapper;

import com.example.demo.action.create.CreateProductActionArgument;
import com.example.demo.action.update.UpdateProductActionArgument;
import com.example.demo.api.product.dto.CreateProductDto;
import com.example.demo.api.product.dto.ProductDto;
import com.example.demo.api.product.dto.SearchProductDto;
import com.example.demo.api.product.dto.UpdateProductDto;
import com.example.demo.model.Product;
import com.example.demo.service.product.argument.SearchProductArgument;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

    SearchProductArgument toSearchArgument(SearchProductDto dto);

    ProductDto toDto(Product product);

    CreateProductActionArgument toCreateActionArgument(CreateProductDto dto);

    UpdateProductActionArgument toUpdateActionArgument(UpdateProductDto dto);
}
