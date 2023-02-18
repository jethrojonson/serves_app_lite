package com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.model;

import com.salesianos.triana.dam.servesapplitebackend.entity.order.model.Order;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.model.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Customer extends User<Customer> {

    private String name;
    private String surname;

    @Column(unique = true, updatable = false)
    private String email;

    @OneToMany(mappedBy = "customer")
    @Builder.Default
    private List<Order> ordersMade = new ArrayList<>();

}
