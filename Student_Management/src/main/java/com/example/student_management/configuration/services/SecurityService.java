package com.example.student_management.configuration.services;

import com.example.student_management.configuration.entities.Role;
import com.example.student_management.configuration.entities.User;

public interface SecurityService {
    User saveUser(String userName,String password,String verifyPassword);
    Role saveRole(String roleName,String description);
    void addRoleToUser(String userName,String roleName);
    void removeRoleFromeUser(String userName,String roleName);
    User loadUserByUserName(String userName);
}
