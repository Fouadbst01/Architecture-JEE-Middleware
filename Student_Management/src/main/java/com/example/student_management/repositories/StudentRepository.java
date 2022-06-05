package com.example.student_management.repositories;

import com.example.student_management.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Page<Student> findByFirstNameStartingWith(String Name, Pageable pageable);
}
