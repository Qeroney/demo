package com.example.demo.action.order;

import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class CreateOrderActionArgument {

    String deliveryName;
    String deliveryPlace;
}
