package com.example.digitalbanking.services;

import com.example.digitalbanking.dtos.CardDTO;
import com.example.digitalbanking.entities.Card;
import com.example.digitalbanking.mappers.BankAccountMapperImpl;
import com.example.digitalbanking.repositories.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class CardServiceImpl implements CardService {
    private CardRepository cardRepository;
    private BankAccountMapperImpl mapper;

    @Override
    public List<CardDTO> getCard(String idAccount) {
        return cardRepository.findAll().stream().map(card -> mapper.fromCard(card)).collect(Collectors.toList());
    }

    @Override
    public CardDTO save(CardDTO cardDTO) {
        Card card = cardRepository.save(mapper.fromCardDTO(cardDTO));
        return mapper.fromCard(card);
    }
}
