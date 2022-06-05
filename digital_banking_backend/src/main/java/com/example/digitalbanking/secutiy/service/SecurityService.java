package com.example.digitalbanking.secutiy.service;

import com.example.digitalbanking.entities.Customer;
import com.example.digitalbanking.secutiy.entities.AppUser;
import com.example.digitalbanking.secutiy.entities.AppRole;
import com.example.digitalbanking.secutiy.exceptions.PasswordIncorrect;
import com.example.digitalbanking.secutiy.exceptions.RoleAlreadyExistsException;
import com.example.digitalbanking.secutiy.exceptions.UserDoesntHaveRole;
import com.example.digitalbanking.secutiy.exceptions.UserNotFoundException;

public interface SecurityService {
    AppUser saveUser(String userName, String password, String verifyPassword) throws PasswordIncorrect;
    AppRole saveRole(String roleName, String description) throws RoleAlreadyExistsException;
    void addRoleToUser(String userName,String roleName) throws UserNotFoundException, RoleAlreadyExistsException;
    void removeRoleFromeUser(String userName,String roleName) throws UserNotFoundException, RoleAlreadyExistsException, UserDoesntHaveRole;
    AppUser loadUserByUserName(String userName) throws UserNotFoundException;
}
