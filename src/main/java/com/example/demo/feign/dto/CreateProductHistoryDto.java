package com.example.demo.feign.dto;

import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateProductHistoryDto {

    private UUID productId;
    private UUID userId;
}
