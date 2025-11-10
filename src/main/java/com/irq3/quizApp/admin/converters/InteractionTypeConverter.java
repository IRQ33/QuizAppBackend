package com.irq3.quizApp.admin.converters;

import com.irq3.quizApp.admin.enums.InteractionType;
import jakarta.persistence.AttributeConverter;

public class InteractionTypeConverter implements AttributeConverter<InteractionType, String> {
    @Override public String convertToDatabaseColumn(InteractionType attribute) {
        return attribute.name();
    }

    @Override public InteractionType convertToEntityAttribute(String dbData) {
        return InteractionType.valueOf(dbData);
    }
}
