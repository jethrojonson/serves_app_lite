package com.salesianos.triana.dam.servesapplitebackend.entity.item.model;

import com.salesianos.triana.dam.servesapplitebackend.entity.product.model.Product;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.model.Company;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "menu_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "company_id",
            foreignKey = @ForeignKey(name = "FK_MENUITEMS_COMPANY")
    )
    private Company company;

    @ManyToOne
    @JoinColumn(
            name = "product_id",
            foreignKey = @ForeignKey(name = "FK_MENUITEMS_PRODUCT")
    )
    private Product product;

    private String category;
    private double price;



}
