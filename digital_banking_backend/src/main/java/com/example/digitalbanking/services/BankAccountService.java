package com.example.digitalbanking.services;

import com.example.digitalbanking.dtos.CurrentAccountDTO;
import com.example.digitalbanking.dtos.CustomerDTO;
import com.example.digitalbanking.entities.Customer;
import com.example.digitalbanking.exceptions.AccountNotFoundException;
import com.example.digitalbanking.exceptions.BalanceInsuffisanteException;
import com.example.digitalbanking.exceptions.CardNotFound;
import com.example.digitalbanking.exceptions.CustomerNotFoundException;
import com.example.digitalbanking.entities.BankAccount;

import java.util.List;

public interface BankAccountService {

    CurrentAccountDTO saveCurrentBankAccout(double balance, double overDraft, String customerId) throws CustomerNotFoundException;
    BankAccount saveSavingBankAccout(double balance, double interestRate, String customerId) throws CustomerNotFoundException;
    BankAccount getBankAccount(String accountId) throws AccountNotFoundException;
    void debit(String accountId,double amount,String description) throws  BalanceInsuffisanteException, AccountNotFoundException;
    void credite(String accountId,double amount,String description) throws  AccountNotFoundException;
    void transfer(String sourceAccountId,String destinationAccountId,double amount) throws BalanceInsuffisanteException, AccountNotFoundException;


    void addCarrdTobankAccount(String idAccount, Long idCard) throws CardNotFound, AccountNotFoundException;
}
