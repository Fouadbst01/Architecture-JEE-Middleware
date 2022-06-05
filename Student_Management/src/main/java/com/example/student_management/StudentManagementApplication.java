package com.example.student_management;

import com.example.student_management.configuration.entities.User;
import com.example.student_management.configuration.repositories.UserRepository;
import com.example.student_management.configuration.services.SecurityService;
import com.example.student_management.entities.Gender;
import com.example.student_management.entities.Student;
import com.example.student_management.repositories.StudentRepository;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Locale;

@SpringBootApplication
public class StudentManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args ->{
            Faker faker = new Faker(Locale.FRENCH);
            for(int i=0;i<100;i++){
                Gender g;
                g=Gender.values()[faker.random().nextInt(2)];
                boolean stat=(faker.random().nextInt(2)!=0);
                Name name = faker.name();
                String firstName=name.firstName();
                String lastName=name.lastName();
                studentRepository.save(new Student(null,
                        firstName.length()>6?firstName:firstName+faker.lorem().characters(5),
                        lastName.length()>6?lastName:lastName+faker.lorem().characters(5),
                        faker.internet().emailAddress(),
                        faker.date().birthday(),
                        g,
                        stat));
            }
        };
    }
    @Bean
    CommandLineRunner commandLineRunner2(SecurityService securityService){
        return args -> {
            securityService.saveUser("fouad","1234","1234");
            securityService.saveUser("admin","1234","1234");

            securityService.saveRole("ADMIN","des");
            securityService.saveRole("USER","des");

            securityService.addRoleToUser("fouad","USER");
            securityService.addRoleToUser("admin","USER");
            securityService.addRoleToUser("admin","ADMIN");

        };
    }

}
