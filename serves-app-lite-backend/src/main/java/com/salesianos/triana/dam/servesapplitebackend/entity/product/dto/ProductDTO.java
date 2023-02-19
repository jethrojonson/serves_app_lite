package com.salesianos.triana.dam.servesapplitebackend.entity.product.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.model.Product;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.view.ProductViews;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.view.CompanyViews;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDTO {

    @JsonView({ProductViews.ProductResponse.class})
    private Long id;

    @JsonView({ProductViews.NewProduct.class, CompanyViews.FullCompanyResponse.class})
    private String productName;

    @JsonView({ProductViews.NewProduct.class, ProductViews.ProductUpdate.class, CompanyViews.FullCompanyResponse.class})
    private String category;

    @JsonView({ProductViews.NewProduct.class, ProductViews.ProductUpdate.class, CompanyViews.FullCompanyResponse.class})
    private double price;

    @JsonView({ProductViews.FullProductResponse.class})
    private boolean active;

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
