package com.example.digitalbanking.secutiy.service;

import com.example.digitalbanking.secutiy.entities.AppUser;
import com.example.digitalbanking.secutiy.entities.AppRole;
import com.example.digitalbanking.secutiy.exceptions.PasswordIncorrect;
import com.example.digitalbanking.secutiy.exceptions.RoleAlreadyExistsException;
import com.example.digitalbanking.secutiy.exceptions.UserDoesntHaveRole;
import com.example.digitalbanking.secutiy.exceptions.UserNotFoundException;
import com.example.digitalbanking.secutiy.repositories.AppUserRepository;
import com.example.digitalbanking.secutiy.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;

@Service
@Transactional
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public AppUser saveUser(String userName, String password, String verifyPassword) throws PasswordIncorrect {
        if( ! password.equals(verifyPassword)) throw  new PasswordIncorrect("password Incorrect");
        AppUser appUser = new AppUser();
        appUser.setId(UUID.randomUUID().toString());
        appUser.setUserName(userName);
        appUser.setPassword(passwordEncoder.encode(password));
        AppUser savedAppUser = appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveRole(String roleName, String description) throws RoleAlreadyExistsException {
        Optional<AppRole> roleOp = roleRepository.findRoleByName(roleName);
        if(roleOp.isPresent()) throw new RoleAlreadyExistsException("Role already Exists");
        AppRole role = new AppRole();
        role.setName(roleName);
        role.setDescription(description);
        AppRole savedRole = roleRepository.save(role);
        return savedRole;
    }

    @Override
    public void addRoleToUser(String userName, String roleName) throws UserNotFoundException, RoleAlreadyExistsException {
        AppUser appUser = appUserRepository.findByUserName(userName).orElseThrow(
                ()-> new UserNotFoundException("User not found")
        );
        AppRole role = roleRepository.findRoleByName(roleName).orElseThrow(
                ()-> new RoleAlreadyExistsException("Role alrady exists")
        );
        //it will be add automatically to database because it is transactional method
        appUser.getRoles().add(role);
    }

    @Override
    public void removeRoleFromeUser(String userName, String roleName) throws UserNotFoundException, RoleAlreadyExistsException, UserDoesntHaveRole {
        AppUser appUser = appUserRepository.findByUserName(userName).orElseThrow(
                ()-> new UserNotFoundException("User not found")
        );
        AppRole role = roleRepository.findRoleByName(roleName).orElseThrow(
                ()-> new RoleAlreadyExistsException("Role alrady exists")
        );
        if(!appUser.getRoles().contains(role)) throw new UserDoesntHaveRole("User doesn't have role ");
        appUser.getRoles().remove(role);
    }

    @Override
    public AppUser loadUserByUserName(String userName) throws UserNotFoundException {
        AppUser appUser = appUserRepository.findByUserName(userName).orElseThrow(
                ()-> new UserNotFoundException(format("User: %s, not found", userName))
        );
        return appUser;
    }
}
