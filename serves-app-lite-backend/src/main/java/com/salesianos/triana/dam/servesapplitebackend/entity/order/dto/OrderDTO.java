package com.salesianos.triana.dam.servesapplitebackend.entity.order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.servesapplitebackend.entity.line.dto.LineDTO;
import com.salesianos.triana.dam.servesapplitebackend.entity.line.model.Line;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.model.Order;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.view.OrderViews;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.dto.CompanyDTO;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.model.Company;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.model.Customer;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDTO {

    @JsonView(OrderViews.OrderResponse.class)
    private Long id;

    @JsonView(OrderViews.OrderResponse.class)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime orderedAt;

    private CompanyDTO company;

    @JsonView(OrderViews.OrderResponse.class)
    private String companyName;

    private Customer customer;

    @JsonView(OrderViews.OrderResponse.class)
    private String username;

    @JsonView(OrderViews.OrderResponse.class)
    private List<LineDTO> salesLines;

    @JsonView(OrderViews.OrderResponse.class)
    private double total;

//    public static OrderDTO of (Order o){
//        return OrderDTO.builder()
//                .id(o.getId())
//                .orderedAt(o.getOrderedAt())
//                .companyName(o.getCompanyName())
//                .username(o.getUsername())
//                .salesLines(o.getSalesLines().stream().map(LineDTO::of).toList())
//                .total(o.getTotal())
//                .build();
//    }

    public static Order of (OrderDTO o){
        return Order.builder()
                .build();
    }
}
