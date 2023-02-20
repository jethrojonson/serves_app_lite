package com.salesianos.triana.dam.servesapplitebackend.entity.order.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.servesapplitebackend.entity.line.model.Line;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.model.Order;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.view.OrderViews;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.model.Company;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.model.Customer;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDTO {

    private Long id;
    private LocalDateTime orderedAt;

    @JsonView({OrderViews.NewOrder.class})
    private Company company;

    private String companyName;

    @JsonView({OrderViews.NewOrder.class})
    private Customer customer;

    private String username;

    @JsonView({OrderViews.NewOrder.class})
    private List<Line> salesLines;

    private double total;

    public static OrderDTO of (Order o){
        return OrderDTO.builder()
                .build();
    }

    public static Order of (OrderDTO o){
        return Order.builder()
                .company(o.getCompany())
                .customer(o.getCustomer())
//                .salesLines()
                .username(o.getCustomer().getUsername())
                .companyName(o.getCompanyName())
                .build();
    }
}
