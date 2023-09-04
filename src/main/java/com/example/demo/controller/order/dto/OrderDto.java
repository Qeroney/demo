package com.example.demo.controller.order.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDto {

    private UUID id;

    private Long finalPrice;

    private String deliveryName;
    private String deliveryPlace;

    @CreationTimestamp
    private LocalDateTime orderTime;

}
