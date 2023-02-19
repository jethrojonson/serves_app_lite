package com.salesianos.triana.dam.servesapplitebackend.entity.product.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String productName;
    private String category;
    private double price;

    @Builder.Default
    private boolean active = true;

}
