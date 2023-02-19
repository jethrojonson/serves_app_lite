package com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.model.Order;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.model.Customer;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.view.CustomerViews;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerDTO {

    @JsonView({CustomerViews.CustomerResponse.class})
    private UUID id;

    @JsonView({CustomerViews.NewCustomer.class})
    private String username;

    @JsonView({CustomerViews.NewCustomer.class, CustomerViews.CustomerUpdate.class})
    private String name;

    @JsonView({CustomerViews.NewCustomer.class, CustomerViews.CustomerUpdate.class})
    private String surname;

    @JsonView({CustomerViews.NewCustomer.class})
    private String email;

    @JsonView({CustomerViews.CustomerResponse.class})
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime subscribedAt;

    @JsonView({CustomerViews.FullCustomerResponse.class})
    private int totalOrders;

    @JsonView({CustomerViews.FullCustomerResponse.class})
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Builder.Default
    private List<Order> ordersMade = new ArrayList<>();

    public static CustomerDTO of (Customer c){
        return CustomerDTO.builder()
                .id(c.getId())
                .username(c.getUsername())
                .name(c.getName())
                .surname(c.getSurname())
                .email(c.getEmail())
                .subscribedAt(c.getCreatedAt())
                .totalOrders(c.getOrdersMade().size())
                .ordersMade(c.getOrdersMade())
                .build();
    }

    public static Customer of (CustomerDTO c){
        return Customer.builder()
                .id(c.getId())
                .username(c.getUsername())
                .name(c.getName())
                .surname(c.getSurname())
                .email(c.getEmail())
                .createdAt(c.getSubscribedAt())
                .ordersMade(c.getOrdersMade())
                .build();
    }



}
