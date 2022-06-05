package com.example.student_management.configuration.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Entity @AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    private String id;
    @Column(unique = true,nullable = false)
    private String userName;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

}
