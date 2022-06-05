package com.example.digitalbanking.secutiy.repositories;

import com.example.digitalbanking.secutiy.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,String> {
    Optional<AppUser> findByUserName(String userName);
}
