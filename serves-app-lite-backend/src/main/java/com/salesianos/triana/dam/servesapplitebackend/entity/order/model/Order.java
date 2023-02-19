package com.salesianos.triana.dam.servesapplitebackend.entity.order.model;

import com.salesianos.triana.dam.servesapplitebackend.entity.line.model.Line;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.model.Company;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.model.Customer;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    private LocalDateTime orderedAt;

    @ManyToOne
    @JoinColumn(
            name = "company_id",
            foreignKey = @ForeignKey(name = "FK_ORDERS_COMPANIES")
    )
    private Company company;

    @ManyToOne
    @JoinColumn(
            name = "customer_id",
            foreignKey = @ForeignKey(name = "FK_ORDERS_CUSTOMERS")
    )
    private Customer customer;

    @OneToMany(
            mappedBy = "order",
            orphanRemoval = true,
            cascade = CascadeType.MERGE
    )
    @Builder.Default
    private List<Line> salesLine = new ArrayList<>();

    private double total;

    //----------------------//
    //* HELPERS - CUSTOMER *//
    //----------------------//

    public void addToCustomer(Customer c){
        c.getOrdersMade().add(this);
        customer = c;
    }

    public void removeFromCustomer(Customer c){
        c.getOrdersMade().remove(this);
        customer = null;
    }

    //---------------------//
    //* HELPERS - COMPANY *//
    //---------------------//

    public void addToCompany(Company c){
        c.getOrdersReceived().add(this);
        company = c;
    }

    public void removeFromCompany(Company c){
        c.getOrdersReceived().remove(this);
        company = null;
    }


}
