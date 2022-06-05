package com.example.digitalbanking.secutiy.repositories;

import com.example.digitalbanking.secutiy.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<AppRole,Long> {
    Optional<AppRole> findRoleByName(String Name);
}
