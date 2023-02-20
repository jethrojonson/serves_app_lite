package com.salesianos.triana.dam.servesapplitebackend.entity.order.service;

import com.salesianos.triana.dam.servesapplitebackend.entity.order.dto.OrderDTO;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.model.Order;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order createNewOrder(Order newOrder){
        return orderRepository.save(newOrder);
    }
}
