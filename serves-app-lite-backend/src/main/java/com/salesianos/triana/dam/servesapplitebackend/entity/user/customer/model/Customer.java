package com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.model;

import com.salesianos.triana.dam.servesapplitebackend.entity.order.model.Order;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.model.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedEntityGraph(
        name = "customer-orders",
        attributeNodes = {
                @NamedAttributeNode(
                        value = "ordersMade"
                )
        }
)
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

    @PreRemove
    public void preRemoveActions(){
        ordersMade.forEach(o->o.setCustomer(null));
        ordersMade.clear();
    }
}
