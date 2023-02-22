package com.salesianos.triana.dam.servesapplitebackend.entity.user.company.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.dto.ProductDTO;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.model.Product;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.model.Company;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.view.CompanyViews;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CompanyDTO {

    @JsonView({CompanyViews.CompanyResponse.class})
    private UUID id;

    @JsonView({CompanyViews.NewCompany.class,CompanyViews.CompanyResponse.class})
    private String cif;

    @JsonView({CompanyViews.NewCompany.class,CompanyViews.CompanyResponse.class, CompanyViews.CompanyUpdate.class})
    private String companyName;

    @JsonView({CompanyViews.NewCompany.class, CompanyViews.CompanyResponse.class})
    private String username;

    @JsonView({CompanyViews.NewCompany.class,CompanyViews.CompanyResponse.class})
    private String avatar;

    @JsonView({CompanyViews.NewCompany.class})
    private String password;

    @JsonView({CompanyViews.NewCompany.class})
    private String verifyPassword;

    @JsonView({CompanyViews.CompanyResponse.class})
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime subscribedAt;

    @JsonView({CompanyViews.FullCompanyResponse.class})
    private List<ProductDTO> menu;


//    public static Company of (CompanyDTO c){
//        return Company.builder()
//                .id(c.getId())
//                .cif(c.getCif())
//                .avatar(c.getAvatar())
//                .companyName(c.getCompanyName())
//                .username(c.getUsername())
//                .password(c.getPassword())
//                .build();
//    }
//
//    public static CompanyDTO of (Company c){
//        return CompanyDTO.builder()
//                .id(c.getId())
//                .cif(c.getCif())
//                .avatar(c.getAvatar())
//                .companyName(c.getCompanyName())
//                .username(c.getUsername())
//                .subscribedAt(c.getCreatedAt())
//                .menu(c.getMenu().stream().map(ProductDTO::of).toList())
//                .build();
//    }
}
