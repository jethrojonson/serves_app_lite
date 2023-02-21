package com.salesianos.triana.dam.servesapplitebackend.search.spec;

import com.salesianos.triana.dam.servesapplitebackend.search.util.QueryableEntity;
import com.salesianos.triana.dam.servesapplitebackend.search.util.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@AllArgsConstructor
@Builder
public class GenericSpecificationBuilder <T>{

    private List<SearchCriteria> params;
    private Class type;

    public Specification<T> build(){

        List<SearchCriteria> checkedParams = params.stream().
                filter(p -> QueryableEntity.checkQueryParam(type, p.getKey()))
                .toList();

        if (checkedParams.isEmpty())
            return null;

        //COMPROBAR CON PARAMS EN VEZ DE CHECKEDPARAMS?

        Specification<T> result = new GenericSpecification<T>(checkedParams.get(0));

        for(int i = 1; i < checkedParams.size(); i++) {
            result = result.and(new GenericSpecification<T>(params.get(i)));
        }

        return result;
    }

}
