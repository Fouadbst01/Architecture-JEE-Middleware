package com.example.student_management.configuration.repositories;

import com.example.student_management.configuration.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role getRoleByName(String name);
}
