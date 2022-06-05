package com.example.digitalbanking.secutiy.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class AppUser {
    @Id
    String id;
    String userName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
    @OneToMany(fetch = FetchType.EAGER)
    List<AppRole> roles=new ArrayList<>();
}
