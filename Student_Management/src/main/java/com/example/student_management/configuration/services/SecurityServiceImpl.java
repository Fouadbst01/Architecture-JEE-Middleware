package com.example.student_management.configuration.services;

import com.example.student_management.configuration.entities.Role;
import com.example.student_management.configuration.entities.User;
import com.example.student_management.configuration.repositories.RoleRepository;
import com.example.student_management.configuration.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public User saveUser(String userName, String password, String verifyPassword) {
        if(!password.equals(verifyPassword)) throw new RuntimeException("Password Incorect");
        String hashedPasswd = passwordEncoder.encode(password);
        User user=new User();
        user.setId(UUID.randomUUID().toString());
        user.setUserName(userName);
        user.setPassword(hashedPasswd);
        User userSaved = userRepository.save(user);
        return userSaved;
    }

    @Override
    public Role saveRole(String roleName, String description) {
        Role role = roleRepository.getRoleByName(roleName);
        if(role != null) throw new RuntimeException("Role "+roleName+" already exist");

        role = new Role();
        role.setName(roleName);
        role.setDescription(description);
        Role savedRole = roleRepository.save(role);

        return savedRole;
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user = userRepository.getUserByUserName(userName);
        if(user==null) throw new RuntimeException("User not found");
        Role role = roleRepository.getRoleByName(roleName);
        if(role==null) throw new RuntimeException("Role not found");
        user.getRoles().add(role);
    }

    @Override
    public void removeRoleFromeUser(String userName, String roleName) {
        User user = userRepository.getUserByUserName(userName);
        if(user==null) throw new RuntimeException("User not found");
        Role role = roleRepository.getRoleByName(roleName);
        if(role==null) throw new RuntimeException("Role not found");;
        user.getRoles().remove(role);
    }

    @Override
    public User loadUserByUserName(String userName) {
        return userRepository.getUserByUserName(userName);
    }
}
