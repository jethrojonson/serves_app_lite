package com.salesianos.triana.dam.servesapplitebackend.entity.order.repository;

import com.salesianos.triana.dam.servesapplitebackend.entity.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
