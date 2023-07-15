package com.example.demo.action;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class CreateProductActionArgument {
    String title;

    Long price;

    UUID categoryId;
}
