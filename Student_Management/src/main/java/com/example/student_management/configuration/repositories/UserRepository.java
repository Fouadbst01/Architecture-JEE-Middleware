package com.example.student_management.configuration.repositories;

import com.example.student_management.configuration.entities.User;
import com.example.student_management.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User getUserByUserName(String name);
    Page<User> findUserByUserNameStartingWith(String Name, Pageable pageable);
}
