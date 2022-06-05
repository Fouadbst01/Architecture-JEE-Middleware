package com.example.digitalbanking.dtos;

import lombok.Data;

import java.util.List;


@Data
public class CustomerDTO {
    String id;
    String name;
    String email;
    CurrentAccountDTO currentAccountDTO;
}
