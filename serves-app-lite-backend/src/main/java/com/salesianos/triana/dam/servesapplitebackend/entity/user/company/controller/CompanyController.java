package com.salesianos.triana.dam.servesapplitebackend.entity.user.company.controller;

import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.service.CompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
@Tag(name = "Company Controller", description = "Controller of the companies operations")
public class CompanyController {

    private final CompanyService companyService;

}
