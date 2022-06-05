package com.example.digitalbanking.entities;

import com.example.digitalbanking.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Operation {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date date;
    private double amount;
    @Enumerated(EnumType.STRING)
    private OperationType opType;

    @ManyToOne
    private BankAccount bankAccount;
}