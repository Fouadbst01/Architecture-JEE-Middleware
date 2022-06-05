package com.example.student_management.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    @NotBlank(message = "First Name is required")
    @Size(min=5,message = "First Name  must be at least 5 characters long")
    private String firstName;
    @Column(length = 50)
    @NotBlank(message = "Last Name is required")
    @Size(min=5,message = "Last Name  must be at least 5 characters long")
    private String lastName;
    @Column(length = 50,nullable = false)
    @Email(regexp = ".+@.+\\..+",message = "Invalid email address")
    @NotBlank(message = "Email is required")
    private String Email;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "BirthDay is required")
    private Date birthDay;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Gender is required")
    private Gender gender;

    private boolean situation;
}
