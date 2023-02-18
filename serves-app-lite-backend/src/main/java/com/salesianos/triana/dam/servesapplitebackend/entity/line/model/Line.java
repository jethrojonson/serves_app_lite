package com.salesianos.triana.dam.servesapplitebackend.entity.line.model;

import com.salesianos.triana.dam.servesapplitebackend.entity.line.model.pk.LinePK;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.model.Order;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.model.Product;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "sales_lines")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Line {

    @EmbeddedId
    private LinePK id;

    @OneToOne
    @MapsId("product_id")
    @JoinColumn(
            name = "product_id",
            foreignKey = @ForeignKey(name = "FK_LINE_PRODUCT")
    )
    private Product product;

    @ManyToOne
    @MapsId("order_id")
    @JoinColumn(
            name = "order_id",
            foreignKey = @ForeignKey(name = "FK_LINE_ORDER")
    )
    private Order order;

    private int quantity;
    private double subTotal;

    //------------------//
    //* HELPERS ORDERS *//
    //------------------//

    public void addToOrder(Order o){
        o.getSalesLine().add(this);
        order = o;
    }

    public void removeFromOrder(Order o){
        o.getSalesLine().remove(this);
        order = null;
    }

}
