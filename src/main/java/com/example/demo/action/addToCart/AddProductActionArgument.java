package com.example.demo.action.addToCart;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class AddProductActionArgument {

    UUID productId;
}
