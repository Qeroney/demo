package com.example.demo.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    private UUID id;

    private Long finalPrice;

    private String deliveryName;
    private String deliveryPlace;

    @CreationTimestamp
    LocalDateTime orderTime;

    @OneToOne
    Cart cart;

}
