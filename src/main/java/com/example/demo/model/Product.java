package com.example.demo.model;

import lombok.*;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    UUID id;

    String title;

    Long price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
}
