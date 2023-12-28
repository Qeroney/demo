package com.example.demo.api.cart.mapper;

import com.example.demo.action.addToCart.AddProductToCartActionArgument;
import com.example.demo.api.cart.dto.CartDto;
import com.example.demo.api.category.dto.CategoryDto;
import com.example.demo.api.product.dto.ProductDto;
import com.example.demo.model.Cart;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-28T19:38:11+1000",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (Eclipse Adoptium)"
)
public class CartMapperImpl implements CartMapper {

    @Override
    public AddProductToCartActionArgument toAddProductToCart(UUID productId) {
        if ( productId == null ) {
            return null;
        }

        AddProductToCartActionArgument.AddProductToCartActionArgumentBuilder addProductToCartActionArgument = AddProductToCartActionArgument.builder();

        addProductToCartActionArgument.productId( productId );

        return addProductToCartActionArgument.build();
    }

    @Override
    public CartDto toDto(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartDto.CartDtoBuilder cartDto = CartDto.builder();

        cartDto.id( cart.getId() );
        cartDto.products( productListToProductDtoList( cart.getProducts() ) );

        return cartDto.build();
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

    protected ProductDto productToProductDto(Product product) {
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

    protected List<ProductDto> productListToProductDtoList(List<Product> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductDto> list1 = new ArrayList<ProductDto>( list.size() );
        for ( Product product : list ) {
            list1.add( productToProductDto( product ) );
        }

        return list1;
    }
}
