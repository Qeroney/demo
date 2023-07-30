package com.example.demo.controller.order.dto;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateOrderDto {


    private String deliveryName;
    private String deliveryPlace;


}
