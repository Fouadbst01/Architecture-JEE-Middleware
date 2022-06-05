package com.example.digitalbanking.dtos;

import lombok.Data;

import java.util.List;
@Data
public class CurrentAccountDTO {
    private double balance;
    List<CardDTO> cards;
}
