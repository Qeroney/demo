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
     private UUID id;

     private String title;

     private Long price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
}
