package com.example.demo.api.order.dto;

import lombok.*;

@Value
@Builder
public class CreateOrderDto {

    String deliveryName;

    String deliveryPlace;
}
