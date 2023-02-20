package com.salesianos.triana.dam.servesapplitebackend.entity.order.controller;

import com.salesianos.triana.dam.servesapplitebackend.entity.order.dto.OrderDTO;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.model.Order;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Tag(name = "Order Controller", description = "Controller of the order operations")
public class OrderController {

    private final OrderService orderService;

    //POST: CREATE ORDER path --> "/order/"
    //*CHANGE INPUT CUSTOMER --> AUTHENTICATION (PRINCIPAL)
    @PostMapping("/")
    public ResponseEntity<OrderDTO> createNewOrder(@RequestBody OrderDTO newOrder){
        Order created = orderService.createNewOrder(OrderDTO.of(newOrder));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(OrderDTO.of(created));
    }
}
