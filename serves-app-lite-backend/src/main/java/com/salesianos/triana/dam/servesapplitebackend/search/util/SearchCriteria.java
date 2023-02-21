package com.salesianos.triana.dam.servesapplitebackend.search.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SearchCriteria {

    private String key;
    private String operator;
    private String value;
}
