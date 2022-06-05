package com.example.digitalbanking.dtos;

import com.example.digitalbanking.enums.CardDesigne;
import com.example.digitalbanking.enums.CardType;
import lombok.Data;

@Data
public class CardDTO {
    Long id;
    String cardNum;
    CardDesigne cardDesigne;
    CardType cardType;
}
