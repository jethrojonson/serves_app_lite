package com.salesianos.triana.dam.servesapplitebackend.entity.order.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.servesapplitebackend.entity.line.dto.LineDTO;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.dto.OrderDTO;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.model.Order;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.service.OrderService;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.view.OrderViews;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.model.User;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.model.Customer;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Tag(name = "Order Controller", description = "Controller of the order operations")
public class OrderController {

    private final OrderService orderService;




//    @JsonView({OrderViews.OrderResponse.class})
//    @PostMapping("/{companyName}")
//    public ResponseEntity<OrderDTO> createNewOrder (
//            @RequestBody List<LineDTO> salesLines,
//            @AuthenticationPrincipal Customer customerLogged,
//            @PathVariable String companyName){
//
//        Order created = orderService
//                .createNewOrder(
//                        (User) customerLogged, companyName,
//                        salesLines.stream().map(LineDTO::of).toList()
//                );
//
//        URI uri = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(created.getId())
//                .toUri();
//
//        return ResponseEntity.created(uri).body(OrderDTO.of(created));
//    }


}
