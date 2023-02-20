package com.salesianos.triana.dam.servesapplitebackend.entity.product.dto;

import com.salesianos.triana.dam.servesapplitebackend.entity.product.model.Product;
import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EditProductDTO {

    @NotEmpty
    private String category;

    @DecimalMin(value = "0.001")
    private double price;

    public static Product of (EditProductDTO p){
        return Product.builder()
                .category(p.getCategory())
                .price(p.getPrice())
                .build();
    }

}
