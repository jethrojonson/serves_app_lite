package com.salesianos.triana.dam.servesapplitebackend.entity.user.company.model;

import com.salesianos.triana.dam.servesapplitebackend.entity.item.model.Item;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.model.Order;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.model.Product;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.model.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedEntityGraph(
        name = "company-menu",
        attributeNodes = {
                @NamedAttributeNode(
                        value = "menu"
                )
        }
)
@NamedEntityGraph(
        name = "company-orders",
        attributeNodes = {
                @NamedAttributeNode(
                        value = "ordersReceived"
                )
        }
)
@Entity
@Table(name = "companies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Company extends User {

    /*Este CIF se compone de una letra más ocho dígitos,
      que suman 9 cifras alfanuméricas: El primer dígito
      indica el tipo de organización o estructura empresarial
      (por ejemplo, A para las sociedades anónimas, o B
      para las sociedades de responsabilidad limitada o SL).
      Cada tipo de empresa tiene su propia letra.*/

    @Column(unique = true, updatable = false)
    private String cif;

    private String companyName;

    @OneToMany
    @JoinTable(
            name = "Menu",
            joinColumns = @JoinColumn(
                    foreignKey = @ForeignKey(name = "FK_MENU_COMPANY")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "product_id",
                    foreignKey = @ForeignKey(name = "FK_MENU_PRODUCT")
            )
    )
    @Builder.Default
    private List<Product> menu = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    @Builder.Default
    private List<Item> menuItems = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    @Builder.Default
    private List<Order> ordersReceived = new ArrayList<>();

    @PreRemove
    public void preRemoveActions(){
        ordersReceived.forEach(o-> o.setCompany(null));
        ordersReceived.clear();
    }



}
