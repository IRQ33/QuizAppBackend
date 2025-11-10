package com.irq3.quizApp.utils;

import com.irq3.quizApp.auth.enums.Permissions;
import com.irq3.quizApp.auth.models.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class AdminGetter {

    public static User getAdmin(){
        var admin = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!admin.getPermissions().contains(Permissions.ADMIN)){
            throw new RuntimeException("You don't have permissions to do it");
        }
        return admin;
    }
}
