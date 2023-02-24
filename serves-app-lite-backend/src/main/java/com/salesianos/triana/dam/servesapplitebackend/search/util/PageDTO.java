package com.salesianos.triana.dam.servesapplitebackend.search.util;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.view.ProductViews;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.view.CompanyViews;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
public class PageDTO<T> {

    @JsonView({ProductViews.ProductResponse.class, CompanyViews.CompanyListItem.class})
    private int resultPerPage;

    @JsonView({ProductViews.ProductResponse.class, CompanyViews.CompanyListItem.class})
    private List<T> results;

    @JsonView({ProductViews.ProductResponse.class, CompanyViews.CompanyListItem.class})
    private Long totalResults;

    @JsonView({ProductViews.ProductResponse.class, CompanyViews.CompanyListItem.class})
    private int page;

    @JsonView({ProductViews.ProductResponse.class, CompanyViews.CompanyListItem.class})
    private int totalPages;

    public static PageDTO of (Page page){
        return PageDTO.builder()
                .resultPerPage(page.getSize())
                .results(page.getContent())
                .totalResults(page.getTotalElements())
                .page(page.getNumber())
                .totalPages(page.getTotalPages())
                .build();
    }
}
