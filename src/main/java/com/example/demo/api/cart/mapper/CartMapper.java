package com.example.demo.api.cart.mapper;

import com.example.demo.action.addToCart.AddProductToCartActionArgument;
import com.example.demo.api.cart.dto.CartDto;
import com.example.demo.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper
public interface CartMapper {

    CartMapper CART_MAPPER = Mappers.getMapper(CartMapper.class);

    AddProductToCartActionArgument toAddProductToCart(UUID productId);

    CartDto toDto(Cart cart);
}
