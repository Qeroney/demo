package com.example.demo.service.order.argument;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CreateOrderArgument {

    String deliveryName;
    String deliveryPlace;
    Long finalPrice;
    Cart cart;

}
