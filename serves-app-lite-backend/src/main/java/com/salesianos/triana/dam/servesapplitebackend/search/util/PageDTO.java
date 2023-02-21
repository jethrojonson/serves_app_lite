package com.salesianos.triana.dam.servesapplitebackend.search.util;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
public class PageDTO<T> {

    private int resultPerPage;
    private List<T> results;
    private Long totalResults;
    private int page;
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
