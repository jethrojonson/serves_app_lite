package com.salesianos.triana.dam.servesapplitebackend.entity.user.company.model;

import com.salesianos.triana.dam.servesapplitebackend.entity.order.model.Order;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.model.Product;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.model.User;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Company extends User<Company> {

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
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @Builder.Default
    private List<Product> menu = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    @Builder.Default
    private List<Order> ordersReceived = new ArrayList<>();



}
