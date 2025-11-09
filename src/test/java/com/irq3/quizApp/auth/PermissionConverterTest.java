package com.irq3.quizApp.auth;

import com.irq3.quizApp.auth.converters.PermissionConverter;
import com.irq3.quizApp.auth.enums.Permissions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PermissionConverterTest {
    PermissionConverter permissionConverter;

    @Test
    public void testConverter() {
        permissionConverter = new PermissionConverter();
        List<Permissions> permissions = List.of(Permissions.USER, Permissions.ADMIN);
        String perms = permissionConverter.convertToDatabaseColumn(permissions);
        assertEquals(permissions, permissionConverter.convertToEntityAttribute(perms));

    }
}
