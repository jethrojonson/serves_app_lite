package com.salesianos.triana.dam.servesapplitebackend.search.spec;

import com.salesianos.triana.dam.servesapplitebackend.search.util.SearchCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@AllArgsConstructor
public class GenericSpecification <T> implements Specification<T> {

    private SearchCriteria searchCriteria;


    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Class type = root.get(searchCriteria.getKey()).getJavaType();
        String key = searchCriteria.getKey();
        String operator = searchCriteria.getOperator();
        String value = searchCriteria.getValue();
        if (operator.equalsIgnoreCase(">")){
            if(isTemporal(type))
                return criteriaBuilder.greaterThanOrEqualTo(root.get(key), getTemporalValue(value, type));
            return criteriaBuilder.greaterThanOrEqualTo(root.<String>get(key), value.toString());
        }else if (operator.equalsIgnoreCase("<")) {
            if(isTemporal(type))
                return criteriaBuilder.lessThanOrEqualTo(root.get(key), getTemporalValue(value, type));
            return criteriaBuilder.lessThanOrEqualTo(root.<String>get(key), value.toString());
        }else if (operator.equalsIgnoreCase(":")) {
            if(isString(type)) {
                if(value.startsWith("*"))
                    return criteriaBuilder.like(root.get(key), value.substring(1)+"%");
                else
                    return criteriaBuilder.like(root.get(key), "%" + value.toString() + "%");
            }
            else if (isBoolean(type)){
                if(isValidBooleanValue(value.toString()))
                    return criteriaBuilder.equal(root.get(key), getBooleanValue(value.toString()));
                else
                    return null;
            }else if(isTemporal(type))
                return criteriaBuilder.equal(root.get(key), getTemporalValue(value, type));
            else
                return criteriaBuilder.equal(root.<String>get(key), value);
        }
        return null;
    }

    //-----------------------------------------------AUX-----------------------------------------------//

    private boolean isTemporal(Class clazz) {
        return Arrays.stream(clazz.getInterfaces()).anyMatch(c -> c.getName() == "java.time.temporal.Temporal");
    }

    private boolean isLocalDate(Class clazz) {
        return clazz == LocalDate.class;
    }

    private boolean isLocalDateTime(Class clazz) {
        return clazz == LocalDateTime.class;
    }

    private boolean isLocalTime(Class clazz) {
        return clazz == LocalTime.class;
    }

    private boolean isString(Class clazz) {
        return clazz == String.class;
    }

    private boolean isBoolean(Class clazz){
        return clazz.getName().toLowerCase().contains("boolean");
    }

    private Comparable getTemporalValue(Object value, Class clazz) {
        Comparable result;
        if (isLocalDate(clazz)) {
            // Patrón por defecto: yyyy-mm-dd
            result = LocalDate.parse(value.toString());
        } else if (isLocalDateTime(clazz)) {
            // Patrón por defecto: yyyy-mm-dd hh24:mi:ss -- NO VALE POR LOS :. Hay que adaptarlo. Por ejemplo yyyy-MM-dd hh24_mi_ss
            String pattern = "yyyy-MM-dd HH_mm_ss";
            result = LocalDateTime.parse(value.toString(), DateTimeFormatter.ofPattern(pattern));
        } else if (isLocalTime(clazz)) {
            // Patrón por defecto: hh24:mi:ss -- NO VALE POR LOS :. Hay que adaptarlo. Por ejemplo hh24_mi_ss
            String pattern = "HH_mm_ss";
            result = LocalTime.parse(value.toString(), DateTimeFormatter.ofPattern(pattern));
        } else
            result = null;

        return result;
    }

    private boolean getBooleanValue(String value) {
        return (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("1"));

    }

    private boolean isValidBooleanValue(String value) {
        return (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("1")
                || value.equalsIgnoreCase("false") || value.equalsIgnoreCase("0")
        );
    }

}
