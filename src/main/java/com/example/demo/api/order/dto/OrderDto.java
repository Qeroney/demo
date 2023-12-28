package com.example.demo.api.order.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class OrderDto {

    UUID id;

    Long finalPrice;

    String deliveryName;
    String deliveryPlace;

    @CreationTimestamp
    LocalDateTime orderTime;
}
