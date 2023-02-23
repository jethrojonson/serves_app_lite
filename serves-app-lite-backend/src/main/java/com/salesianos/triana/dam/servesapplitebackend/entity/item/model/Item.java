package com.salesianos.triana.dam.servesapplitebackend.entity.item.model;

import com.salesianos.triana.dam.servesapplitebackend.entity.item.model.pk.ItemPK;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.model.Product;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.model.Company;
import lombok.*;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;

@Entity
@Table(name = "menu_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Item {

    @EmbeddedId
    private ItemPK id;


    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(
            name = "product_id",
            foreignKey = @ForeignKey(name = "FK_MENUITEMS_PRODUCT")
    )
    private Product product;

    @ManyToOne
    @MapsId("company_id")
    @JoinColumn(
            name = "company_id",
            foreignKey = @ForeignKey(name = "FK_MENUITEMS_COMPANY")
    )
    private Company company;

    private String category;
    private double price;
}
