package com.example.digitalbanking.services;

import com.example.digitalbanking.dtos.CardDTO;
import com.example.digitalbanking.dtos.CustomerDTO;

import java.util.List;

public interface CardService {
    List<CardDTO> getCard(String idAccount);

    CardDTO save(CardDTO cardDTO);
}
