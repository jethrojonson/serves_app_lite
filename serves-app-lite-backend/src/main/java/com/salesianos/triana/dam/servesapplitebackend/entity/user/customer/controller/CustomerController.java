package com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.controller;

import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@Tag(name = "Customer Controller", description = "Controller of the customers operations")
public class CustomerController {

    private final CustomerService customerService;
}
