package com.salesianos.triana.dam.servesapplitebackend.entity.item.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.servesapplitebackend.entity.item.model.Item;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.view.CompanyViews;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ItemDTO {

    private String companyName;

    @JsonView({CompanyViews.CompanySimpleResponse.class, CompanyViews.FullCompanyResponse.class})
    private String productName;

    @JsonView({CompanyViews.CompanySimpleResponse.class, CompanyViews.FullCompanyResponse.class})
    private double price;

    @JsonView({CompanyViews.CompanySimpleResponse.class, CompanyViews.FullCompanyResponse.class})
    private String category;

    public static Item of(ItemDTO i){
        return Item.builder()
                .price(i.getPrice())
                .category(i.getCategory())
                .build();
    }

    public static ItemDTO of(Item i){
        return ItemDTO.builder()
                .companyName(i.getCompany().getCompanyName())
                .productName(i.getProduct().getProductName())
                .price(i.getPrice())
                .category(i.getCategory())
                .build();
    }
}
