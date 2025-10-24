package com.irq3.quizApp.auth.converters;


import com.irq3.quizApp.auth.enums.Permissions;
import jakarta.persistence.AttributeConverter;

import java.util.ArrayList;
import java.util.List;

public class PermissionConverter implements AttributeConverter<List<Permissions>,String> {

    @Override public String convertToDatabaseColumn(List<Permissions> permissions) {
        StringBuilder sb = new StringBuilder();
        for (var p : permissions){
            sb.append(p.name()).append(",");
        }
        return sb.toString();
    }

    @Override public List<Permissions> convertToEntityAttribute(String s) {
        String[] array = s.split(",");
        List<Permissions> permissions = new ArrayList<>(array.length);
        for(var p : array){
            permissions.add(Permissions.valueOf(p.trim()));
        }
        return permissions;
    }
}
