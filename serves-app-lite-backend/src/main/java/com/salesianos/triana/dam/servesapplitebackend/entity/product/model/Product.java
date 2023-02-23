package com.salesianos.triana.dam.servesapplitebackend.entity.product.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.view.OrderViews;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@SQLDelete(sql = "UPDATE products SET active = false WHERE id=?")
@Where(clause = "active = true")
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
