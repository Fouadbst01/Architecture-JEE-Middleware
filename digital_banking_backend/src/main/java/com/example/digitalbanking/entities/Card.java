package com.example.digitalbanking.entities;

import com.example.digitalbanking.enums.CardDesigne;
import com.example.digitalbanking.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Card {
    @Id
    @GeneratedValue
    Long id;
    String cardNum;
    CardDesigne cardDesigne;
    CardType cardType;

    @ManyToOne
    BankAccount bankAccount;
    @ManyToOne
    CurrentAccount currentAccount;
}
