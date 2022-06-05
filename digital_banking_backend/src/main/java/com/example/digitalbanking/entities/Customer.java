package com.example.digitalbanking.entities;

import com.example.digitalbanking.secutiy.entities.AppUser;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Customer{
    @Id
    private String id;
    private String name;
    private String email;
    @OneToMany(mappedBy = "customer")
    private List<BankAccount> bankAccounts;

}
