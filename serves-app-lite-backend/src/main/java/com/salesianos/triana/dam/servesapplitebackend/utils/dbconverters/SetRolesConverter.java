package com.salesianos.triana.dam.servesapplitebackend.utils.dbconverters;

import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.roles.UserRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class SetRolesConverter implements AttributeConverter<Set<UserRole>, String> {

    private final String SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(Set<UserRole> attribute) {
        if(!attribute.isEmpty())
            return attribute.stream()
                    .map(UserRole::name)
                    .collect(Collectors.joining(SEPARATOR));
        return null;
    }

    @Override
    public Set<UserRole> convertToEntityAttribute(String dbData) {
        if(dbData != null && !dbData.isBlank())
            return Arrays.stream(dbData.split(SEPARATOR))
                    .map(UserRole::valueOf)
                    .collect(Collectors.toCollection(()-> new HashSet<UserRole>()));
        return new HashSet<UserRole>();
    }
}
