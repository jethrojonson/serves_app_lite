package com.salesianos.triana.dam.servesapplitebackend.entity.line.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.servesapplitebackend.entity.line.model.Line;
import com.salesianos.triana.dam.servesapplitebackend.entity.line.model.pk.LinePK;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.view.OrderViews;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LineDTO {

    private Long product_id;

    @JsonView(OrderViews.OrderResponse.class)
    private int quantity;

    @JsonView(OrderViews.OrderResponse.class)
    private String productName;

    @JsonView(OrderViews.OrderResponse.class)
    private double subTotal;

//    public static Line of (LineDTO l){
//        return Line.builder()
//                .id(
//                        LinePK.builder()
//                                .product_id(l.getProduct_id())
//                                .build()
//                )
//                .quantity(l.getQuantity())
//                .build();
//    }
//
//    public static LineDTO of (Line l){
//        return LineDTO.builder()
//                .productName(l.getProduct().getProductName())
//                .quantity(l.getQuantity())
//                .subTotal(l.getSubTotal())
//                .build();
//    }
}
