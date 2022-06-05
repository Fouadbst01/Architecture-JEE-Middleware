package com.example.student_management.configuration.services;

import com.example.student_management.configuration.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {
    private SecurityService securityService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=securityService.loadUserByUserName(username);
        Collection<GrantedAuthority> authorities= user.getRoles().stream().map(
                role->new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());
        org.springframework.security.core.userdetails.User userd= new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                authorities
        );
        return userd;
    }
}
