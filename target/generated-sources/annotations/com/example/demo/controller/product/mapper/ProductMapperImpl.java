package com.example.demo.controller.product.mapper;

import com.example.demo.action.create.CreateProductActionArgument;
import com.example.demo.action.update.UpdateProductActionArgument;
import com.example.demo.controller.category.dto.CategoryDto;
import com.example.demo.controller.product.dto.CreateProductDto;
import com.example.demo.controller.product.dto.ProductDto;
import com.example.demo.controller.product.dto.SearchProductDto;
import com.example.demo.controller.product.dto.UpdateProductDto;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.product.argument.SearchProductArgument;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-30T00:49:07+1000",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.7 (Eclipse Adoptium)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public SearchProductArgument toSearchArgument(SearchProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        SearchProductArgument.SearchProductArgumentBuilder searchProductArgument = SearchProductArgument.builder();

        searchProductArgument.productTitle( dto.getProductTitle() );
        searchProductArgument.categoryTitle( dto.getCategoryTitle() );

        return searchProductArgument.build();
    }

    @Override
    public ProductDto toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto.ProductDtoBuilder productDto = ProductDto.builder();

        productDto.id( product.getId() );
        productDto.title( product.getTitle() );
        productDto.price( product.getPrice() );
        productDto.category( categoryToCategoryDto( product.getCategory() ) );

        return productDto.build();
    }

    @Override
    public CreateProductActionArgument toCreateActionArgument(CreateProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        CreateProductActionArgument.CreateProductActionArgumentBuilder createProductActionArgument = CreateProductActionArgument.builder();

        createProductActionArgument.title( dto.getTitle() );
        createProductActionArgument.price( dto.getPrice() );
        createProductActionArgument.categoryId( dto.getCategoryId() );

        return createProductActionArgument.build();
    }

    @Override
    public UpdateProductActionArgument toUpdateActionArgument(UpdateProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        UpdateProductActionArgument.UpdateProductActionArgumentBuilder updateProductActionArgument = UpdateProductActionArgument.builder();

        updateProductActionArgument.title( dto.getTitle() );
        updateProductActionArgument.price( dto.getPrice() );
        updateProductActionArgument.categoryId( dto.getCategoryId() );

        return updateProductActionArgument.build();
    }

    protected CategoryDto categoryToCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto.CategoryDtoBuilder categoryDto = CategoryDto.builder();

        categoryDto.id( category.getId() );
        categoryDto.title( category.getTitle() );

        return categoryDto.build();
    }
}
