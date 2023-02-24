package com.salesianos.triana.dam.servesapplitebackend.entity.line.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.servesapplitebackend.entity.item.model.Item;
import com.salesianos.triana.dam.servesapplitebackend.entity.line.model.pk.LinePK;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.model.Order;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.view.OrderViews;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.model.Product;
import lombok.*;

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
    @MapsId("item_id")
    @JoinColumn(
            name = "item_id",
            foreignKey = @ForeignKey(name = "FK_SALESLINES_MENUITEMS")
    )
    private Item item;

    @ManyToOne
    @MapsId("order_id")
    @JoinColumn(
            name = "order_id",
            foreignKey = @ForeignKey(name = "FK_SALESLINES_ORDERS")
    )
    private Order order;

    private int quantity;
    private double subTotal;

    //------------------//
    //* HELPERS ORDERS *//
    //------------------//

    public void addToOrder(Order o){
        o.getSalesLines().add(this);
        order = o;
    }

    public void removeFromOrder(Order o){
        o.getSalesLines().remove(this);
        order = null;
    }

}
