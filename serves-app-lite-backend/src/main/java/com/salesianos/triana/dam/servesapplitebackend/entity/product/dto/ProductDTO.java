package com.salesianos.triana.dam.servesapplitebackend.entity.product.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.model.Product;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.view.ProductViews;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.view.CompanyViews;
import lombok.*;

import javax.validation.constraints.*;
import java.net.URI;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDTO {

    @JsonView({ProductViews.ProductResponse.class})
    private Long id;

    @JsonView({ProductViews.NewProduct.class, CompanyViews.FullCompanyResponse.class})
    @NotEmpty
    private String productName;

    @JsonView({ProductViews.NewProduct.class,CompanyViews.FullCompanyResponse.class})
    @NotEmpty
    private String category;

    @JsonView({ProductViews.NewProduct.class,CompanyViews.FullCompanyResponse.class})
    @NotNull
    @DecimalMin(value = "0.001")
    private double price;

    @JsonView({ProductViews.FullProductResponse.class})
    @Builder.Default
    private boolean active = true;

    public static ProductDTO of (Product p){
        return ProductDTO.builder()
                .id(p.getId())
                .productName(p.getProductName())
                .category(p.getCategory())
                .price(p.getPrice())
                .active(p.isActive())
                .build();
    }

    public static Product of (ProductDTO p){
        return Product.builder()
                .id(p.getId())
                .productName(p.getProductName())
                .category(p.getCategory())
                .price(p.getPrice())
                .active(p.isActive())
                .build();
    }

}
